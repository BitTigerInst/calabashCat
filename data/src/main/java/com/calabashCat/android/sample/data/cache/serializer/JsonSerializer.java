/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.calabashCat.android.sample.data.cache.serializer;
import com.calabashCat.android.sample.data.entities.SearchResponse;
import com.google.gson.Gson;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
public class JsonSerializer {

  private final Gson gson = new Gson();

  public JsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param searchResponse {@link SearchResponse} to serialize.
   */
  public String serialize(SearchResponse searchResponse) {
    String jsonString = gson.toJson(searchResponse, SearchResponse.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link SearchResponse}
   */
  public SearchResponse deserialize(String jsonString) {
    SearchResponse searchResponse = gson.fromJson(jsonString, SearchResponse.class);
    return searchResponse;
  }
}
