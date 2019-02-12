package nikita.shekhovtsov.starwarsexplorer.Queries;


import nikita.shekhovtsov.starwarsexplorer.Model.BeingList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAllPeople {
    @GET("people")
    Call<BeingList> getPeople();
}
