package com.udacity.webcrawler;

import com.udacity.webcrawler.json.CrawlResult;

import com.udacity.webcrawler.parser.PageParserFactory;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import javax.inject.Inject;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;

/**
 * A concrete implementation of {@link WebCrawler} that runs multiple threads on a
 * {@link ForkJoinPool} to fetch and process multiple web pages in parallel.
 */
final class ParallelWebCrawler implements WebCrawler {
  private final Clock clock;
  private final Duration timeout;
  private final int popularWordCount;
  private final ForkJoinPool pool;
  private final int maxDepth;
  private final PageParserFactory parserFactory;
  private final List<Pattern> ignoredUrls;

  @Inject
  ParallelWebCrawler(
      Clock clock,
      @Timeout Duration timeout,
      @PopularWordCount int popularWordCount,
      @TargetParallelism int threadCount,
      @MaxDepth int maxDepth,
      PageParserFactory parserFactory,
      @IgnoredUrls List<Pattern> ignoredUrls) {
    this.clock = clock;
    this.timeout = timeout;
    this.popularWordCount = popularWordCount;
    this.pool = new ForkJoinPool(Math.min(threadCount, getMaxParallelism()));
    this.maxDepth = maxDepth;
    this.parserFactory = parserFactory;
    this.ignoredUrls = ignoredUrls;
  }

  @Override
  public CrawlResult crawl(List<String> startingUrls) {

    Map<String, Integer> wordCounts = new ConcurrentHashMap<>();
    ConcurrentSkipListSet<String> visitedUrls = new ConcurrentSkipListSet<>();
    List<CountWordsAction> countWordsActions = new ArrayList<>();

    // Start clock
    Instant timeLimit = clock.instant().plus(timeout);

    for (final String url : startingUrls) {
      if (clock.instant().isAfter(timeLimit))
        break;

      countWordsActions.add(
          new CountWordsAction.Builder()
              .setMaxDepth(maxDepth)
              .setClock(clock)
              .setTimeLimit(timeLimit)
              .setUrl(url)
              .setWordCounts(wordCounts)
              .setParserFactory(parserFactory)
              .setVisitedUrls(visitedUrls)
              .setIgnoredUrls(ignoredUrls)
              .build()
      );
    }
    for (CountWordsAction task: countWordsActions)
      pool.invoke(task);

    if (!wordCounts.isEmpty())
      wordCounts = WordCounts.sort(wordCounts, popularWordCount);

    return new CrawlResult.Builder()
        .setWordCounts(wordCounts)
        .setUrlsVisited(visitedUrls.size())
        .build();
  }

  @Override
  public int getMaxParallelism() {
    return Runtime.getRuntime().availableProcessors();
  }
}
