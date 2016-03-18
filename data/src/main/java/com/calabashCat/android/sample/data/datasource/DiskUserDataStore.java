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
package com.calabashCat.android.sample.data.datasource;

import com.calabashCat.android.sample.data.cache.UserCache;
import com.calabashCat.android.sample.data.entities.Business;
import com.calabashCat.android.sample.data.entities.SearchResponse;

import java.util.Map;

import rx.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
public class DiskUserDataStore implements UserDataStore {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  public DiskUserDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override
  public Observable<SearchResponse> getSearchResponse() {
    throw new NullPointerException("not implement yet");
  }

  @Override
  public Observable<SearchResponse> getSearchResponse(Map.Entry<Object, Map<String, String>> query) {
    throw new NullPointerException("not implement yet");
  }

  @Override
  public Observable<Business> getBusiness(int userId) {
    throw new NullPointerException("not implement yet");
  }
}
