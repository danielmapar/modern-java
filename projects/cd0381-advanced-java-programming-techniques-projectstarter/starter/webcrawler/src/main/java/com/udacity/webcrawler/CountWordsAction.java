package com.udacity.webcrawler;

import com.udacity.webcrawler.parser.PageParser;
import com.udacity.webcrawler.parser.PageParserFactory;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CountWordsAction extends RecursiveAction {
  private final int maxDepth;
  private final Clock clock;
  private final Instant timeLimit;
  private final String url;
  private final PageParserFactory parserFactory;
  private final Map<String, Integer> wordCounts;
  private final ConcurrentSkipListSet<String> visitedUrls;
  private final List<Pattern> ignoredUrls;

  public CountWordsAction(
      int maxDepth,
      Clock clock,
      Instant timeLimit,
      String url,
      PageParserFactory parserFactory,
      Map<String, Integer> wordCounts,
      ConcurrentSkipListSet<String> visitedUrls,
      List<Pattern> ignoredUrls) {
    this.maxDepth = maxDepth;
    this.clock = clock;
    this.timeLimit = timeLimit;
    this.url = url;
    this.parserFactory = parserFactory;
    this.wordCounts = wordCounts;
    this.visitedUrls = visitedUrls;
    this.ignoredUrls = ignoredUrls;
  }

  @Override
  protected void compute() {
    if (clock.instant().isAfter(timeLimit) || maxDepth == 0)
      return;

    for (final Pattern pattern : ignoredUrls) {
      if (pattern.matcher(url).matches())
        return;
    }

    if (visitedUrls.contains(url))
      return;

    visitedUrls.add(url);

    PageParser.Result result = parserFactory.get(url).parse();
    countWordsInUrl(result, wordCounts);

    List<CountWordsAction> subTasks = result.getLinks()
        .stream()
        .map(link -> new CountWordsAction.Builder()
            .setMaxDepth(maxDepth - 1)
            .setClock(clock)
            .setTimeLimit(timeLimit)
            .setUrl(link)
            .setParserFactory(parserFactory)
            .setWordCounts(wordCounts)
            .setVisitedUrls(visitedUrls)
            .setIgnoredUrls(ignoredUrls)
            .build())
        .collect(Collectors.toList());

    invokeAll(subTasks);
  }

  public static void countWordsInUrl(PageParser.Result result, Map<String, Integer> wordCounts) {
    for (Map.Entry<String, Integer> localWordCount : result.getWordCounts().entrySet()) {
      // Increasing our global word count with our local word count
      wordCounts.compute(
          localWordCount.getKey(), (key, value) ->
              (value == null) ?
                  localWordCount.getValue() :
                  wordCounts.get(localWordCount.getKey()) + localWordCount.getValue()
      );
    }
  }

  public static final class Builder {
    private int maxDepth;
    private Clock clock;
    private Instant timeLimit;
    private String url;
    private PageParserFactory parserFactory;
    private Map<String, Integer> wordCounts;
    private ConcurrentSkipListSet<String> visitedUrls;
    private List<Pattern> ignoredUrls;

    public Builder setMaxDepth(int maxDepth) {
      this.maxDepth = maxDepth;
      return this;
    }

    public Builder setClock(Clock clock) {
      this.clock = clock;
      return this;
    }

    public Builder setTimeLimit(Instant timeLimit) {
      this.timeLimit = timeLimit;
      return this;
    }

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setParserFactory(PageParserFactory parserFactory) {
      this.parserFactory = parserFactory;
      return this;
    }

    public Builder setWordCounts(Map<String, Integer> wordCounts) {
      this.wordCounts = wordCounts;
      return this;
    }

    public Builder setVisitedUrls(ConcurrentSkipListSet<String> visitedUrls) {
      this.visitedUrls = visitedUrls;
      return this;
    }

    public Builder setIgnoredUrls(List<Pattern> ignoredUrls) {
      this.ignoredUrls = ignoredUrls;
      return this;
    }

    public CountWordsAction build() {
      return new CountWordsAction(maxDepth, clock, timeLimit, url,
          parserFactory, wordCounts, visitedUrls, ignoredUrls);
    }
  }
}
