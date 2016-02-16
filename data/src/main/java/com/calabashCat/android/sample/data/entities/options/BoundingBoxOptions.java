package com.calabashCat.android.sample.data.entities.options;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class BoundingBoxOptions {

    /**
     * Southwest latitude of bounding box.
     */
    public abstract Double swLatitude();

    /**
     * Southwest longitude of bounding box.
     */
    public abstract Double swLongitude();

    /**
     * Northeast latitude of bounding box.
     */
    public abstract Double neLatitude();

    /**
     * Northeast longitude of bounding box.
     */
    public abstract Double neLongitude();

    /**
     * String presentation for {@link com.yelp.clientlib.entities.options.BoundingBoxOptions}. The generated string is encoded as
     * "swLatitude,swLongitude%7CneLatitude,neLongitude". This method is used by {@link retrofit.http.Query} to
     * generate the values of query parameters.
     *
     * BoundingBox query param value contains non-suggested URI character '|' which doesn't fit into most of the
     * signature functions, we encode it here into "%7C" so it's not passed through http client.
     *
     * @return String presentation for {@link com.yelp.clientlib.entities.options.BoundingBoxOptions}
     * @see <a href=https://www.yelp.com/developers/documentation/v2/search_api#searchGBB>https://www.yelp.com/developers/documentation/v2/search_api#searchGBB</a>
     */
    @Override
    public String toString() {
        return String.format("%f,%f%%7C%f,%f", swLatitude(), swLongitude(), neLatitude(), neLongitude());
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * Sets southwest latitude.
         *
         * @return this
         */
        public abstract Builder swLatitude(Double latitude);

        /**
         * Sets southwest longitude.
         *
         * @return this
         */
        public abstract Builder swLongitude(Double longitude);

        /**
         * Sets northeast latitude.
         *
         * @return this
         */
        public abstract Builder neLatitude(Double latitude);

        /**
         * Sets northeast longitude.
         *
         * @return this
         */
        public abstract Builder neLongitude(Double longitude);

        /**
         * Returns a reference to the object of {@link com.yelp.clientlib.entities.options.BoundingBoxOptions} being constructed by the builder.
         *
         * @return the {@link com.yelp.clientlib.entities.options.BoundingBoxOptions} constructed by the builder.
         */
        public abstract BoundingBoxOptions build();
    }

    public static Builder builder() {
        return new AutoValue_BoundingBoxOptions.Builder();
    }
}
