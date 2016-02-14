/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.calabashCat.android.sample.data.datasource;

import com.calabashCat.android.sample.data.cache.UserCache;
import com.calabashCat.android.sample.data.net.RestApi;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

	private final RestApi restApi;
	private final UserCache userCache;

	private final Action1<SearchResponse> saveToCacheAction = new Action1<SearchResponse>() {
		@Override
		public void call(SearchResponse searchResponse) {
			if (searchResponse != null) {
				CloudUserDataStore.this.userCache.put(searchResponse);
			}
		}
	};

	/**
	 * Construct a {@link UserDataStore} based on connections to the api (Cloud).
	 *
	 * @param restApi The {@link RestApi} implementation to use.
	 * @param userCache A {@link UserCache} to cache data retrieved from the api.
	 */
	public CloudUserDataStore(RestApi restApi, UserCache userCache) {
		this.restApi = restApi;
		this.userCache = userCache;
	}

	@Override
	public Observable<SearchResponse> getSearchResponse() {
		Map<String, String> params = new HashMap<>();

		// general params
		params.put("term", "food");
		params.put("limit", "3");

		// locale params
		params.put("lang", "fr");
		return this.restApi.search("San Francisco", params);
	}

	@Override
	public Observable<Business> getBusiness(final int userId) {
//		return this.restApi.userEntityById(userId)
//				.doOnNext(saveToCacheAction);
		return null;
	}
}