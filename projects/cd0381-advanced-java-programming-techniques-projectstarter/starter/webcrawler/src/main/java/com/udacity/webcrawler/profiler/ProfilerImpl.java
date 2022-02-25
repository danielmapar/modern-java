package com.udacity.webcrawler.profiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

/**
 * Concrete implementation of the {@link Profiler}.
 */
final class ProfilerImpl implements Profiler {

  private final Clock clock;
  private final ProfilingState profilingState = new ProfilingState();
  private final ZonedDateTime startTime;

  @Inject
  ProfilerImpl(Clock clock) {
    this.clock = Objects.requireNonNull(clock);
    this.startTime = ZonedDateTime.now(clock);
  }

  @Override
  public <T> T wrap(Class<T> klass, T delegate) {
    Objects.requireNonNull(klass);

    Boolean isProfiled = false;
    // Search for method with the @Profiled annotation
    for (Method method : klass.getMethods())
      if (method.isAnnotationPresent(Profiled.class)) {
        isProfiled = true;
        break;
      }

    if (!isProfiled)
      throw new IllegalArgumentException();

    return (T) Proxy.newProxyInstance(
        klass.getClassLoader(),
        new Class<?>[]{klass},
        new ProfilingMethodInterceptor(clock, delegate, profilingState));
  }

  @Override
  public void writeData(Path path) {
    try (FileWriter fileWriter = new FileWriter(path.toString(), true)) {
      BufferedWriter writer = new BufferedWriter(fileWriter);
      this.writeData(writer);
      writer.close();
    } catch (IOException e) {
      System.out.println("Failed to write profile file.");
      e.printStackTrace();
    }
  }

  @Override
  public void writeData(Writer writer) throws IOException {
    writer.write("Run at " + RFC_1123_DATE_TIME.format(startTime));
    writer.write(System.lineSeparator());
    profilingState.write(writer);
    writer.write(System.lineSeparator());
  }
}
