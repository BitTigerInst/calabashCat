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
package com.calabashCat.android.sample.domain.interactor.repository;

import android.content.Context;

import com.calabashCat.android.sample.data.dto.User;
import com.calabashCat.android.sample.data.datasource.UserDataStore;
import com.calabashCat.android.sample.data.datasource.UserDataStoreFactory;
import com.calabashCat.android.sample.data.entities.SearchResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link UserRepository} for retrieving user data.
 */
public class UserDataRepository implements UserRepository {

	private UserDataStoreFactory userDataStoreFactory;

	public UserDataRepository(Context appContext) {
		this.userDataStoreFactory = new UserDataStoreFactory(appContext);
	}

	@Override
	public Observable<SearchResponse> getSearchResponse() {
		final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
		Observable<SearchResponse> searchResponse = userDataStore.getSearchResponse();
		return searchResponse;
	}

	@Override
	public Observable<User> user(int userId) {
		throw new NullPointerException("not implement yet");
	}

	@Override
	public Observable getSearchResponse(Map.Entry<Object, Map<String, String>> query) {
		final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
		Observable<SearchResponse> searchResponse = userDataStore.getSearchResponse(query);
		return searchResponse;
	}
}
