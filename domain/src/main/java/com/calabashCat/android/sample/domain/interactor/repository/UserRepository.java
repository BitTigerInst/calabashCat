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


import com.calabashCat.android.sample.data.dto.User;
import com.calabashCat.android.sample.data.entities.SearchResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface UserRepository {
	/**
	 * Get an {@link Observable} which will emit a List of {@link User}.
	 */
	Observable<SearchResponse> getSearchResponse();

	/**
	 * Get an {@link Observable} which will emit a {@link User}.
	 *
	 * @param userId The user id used to retrieve user data.
	 */
	Observable<User> user(final int userId);

	Observable getSearchResponse(Map.Entry<Object, Map<String, String>> query);
}
