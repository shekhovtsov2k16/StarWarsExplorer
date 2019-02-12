package nikita.shekhovtsov.starwarsexplorer.Queries;

import nikita.shekhovtsov.starwarsexplorer.Model.BeingList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchByName {
    @GET("people/")
    Call<BeingList> getSearchableNames(@Query("search") String searchableName);
}
