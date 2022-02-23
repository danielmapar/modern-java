package com.udacity.webcrawler.json;

import java.io.Reader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() {
    // Using try-resource to close file automatically
    try (Reader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8)) {
      return this.read(reader);
    } catch (IOException e) {
      System.out.println("Failed to load config!");
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  public static CrawlerConfiguration read(Reader reader) {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(reader);

    try {
      ObjectMapper mapper = new ObjectMapper();
      /*
      * AUTO_CLOSE_SOURCE = Feature that determines whether parser will automatically close underlying
      * input source that is NOT owned by the parser. If disabled, calling application has to separately close
      * the underlying InputStream and Reader instances used to create the parser. If enabled,
      * parser will handle closing, as long as parser itself gets closed: this happens when
      * end-of-input is encountered, or parser is closed by a call to JsonParser.close().
      * */
      mapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
      return mapper.readValue(reader, CrawlerConfiguration.class);
    } catch (IOException e) {
      System.out.println("Failed to read config JSON!");
      e.printStackTrace();
      return null;
    }
  }
}
