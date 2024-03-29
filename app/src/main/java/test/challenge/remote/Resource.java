package test.challenge.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import static test.challenge.remote.Status.ERROR;
import static test.challenge.remote.Status.INITIALIZING;
import static test.challenge.remote.Status.LOADING;
import static test.challenge.remote.Status.SUCCESS;


/**
 * A generic class that holds a value with its loading status.
 * @param <Response>
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
public class Resource<Response> {
    @NonNull
    public final Status status;

    @Nullable
    public final Response data;
    // can't be final
    @Nullable private String message;

    private Resource(@NonNull Status status, @Nullable Response data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <Response> Resource<Response> success(@NonNull Response data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <Response> Resource<Response> error(String msg, @Nullable Response data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <Response> Resource<Response> loading(@Nullable Response data) {
        return new Resource<>(LOADING, data, null);
    }

    public static <Response> Resource<Response> initializing(String msg, @Nullable Response data) {
        return new Resource<>(INITIALIZING, data, msg);
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}