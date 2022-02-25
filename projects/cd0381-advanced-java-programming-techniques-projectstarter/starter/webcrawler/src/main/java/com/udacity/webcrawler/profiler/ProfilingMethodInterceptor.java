package com.udacity.webcrawler.profiler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * A method interceptor that checks whether {@link Method}s are annotated with the {@link Profiled}
 * annotation. If they are, the method interceptor records how long the method invocation took.
 */
final class ProfilingMethodInterceptor implements InvocationHandler {

  private final Clock clock;
  private final Object target;
  private final ProfilingState profilingState;

  ProfilingMethodInterceptor(Clock clock, Object target, ProfilingState profilingState) {
    this.clock = Objects.requireNonNull(clock);
    this.target = Objects.requireNonNull(target);
    this.profilingState = Objects.requireNonNull(profilingState);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
    Instant startTime = clock.instant();

    if (method.getAnnotation(Profiled.class) != null) {
      try {
        method.invoke(target, args);
      } catch (InvocationTargetException e) {
        throw e.getTargetException();
      } catch (IllegalArgumentException e) {
        throw new RuntimeException(e);
      } finally {
        profilingState.record(target.getClass(),
            method,
            Duration.between(startTime, clock.instant()));
      }
    }

    return method.invoke(target, args);
  }
}
