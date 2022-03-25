package com.armados.app.epraxeis;

import androidx.annotation.Nullable;

import com.armados.app.epraxeis.diavgeia.Decision;
import com.armados.app.epraxeis.diavgeia.DecisionSearchResult;
import com.armados.app.epraxeis.diavgeia.DecisionTypes;
import com.armados.app.epraxeis.diavgeia.Dictionaries;
import com.armados.app.epraxeis.diavgeia.DictionaryItems;
import com.armados.app.epraxeis.diavgeia.Organization;
import com.armados.app.epraxeis.diavgeia.Organizations;
import com.armados.app.epraxeis.diavgeia.Signer;
import com.armados.app.epraxeis.diavgeia.Signers;
import com.armados.app.epraxeis.diavgeia.Unit;
import com.armados.app.epraxeis.diavgeia.Units;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({"Accept: application/json"})
    @GET("search/advanced")
    Call<DecisionSearchResult> getDecisionsAdvanced(
            @Nullable @Query("q") String q,
            @Nullable @Query("page") Integer page,
            @Nullable @Query("size") Integer size
    );

    @Headers({"Accept: application/json"})
    @GET("decisions/{ada}")
    Call<Decision> getDecisionByAda(@Path("ada") String ada);

    @Headers({"Accept: application/json"})
    @GET("signers/{id}")
    Call<Signer> getSignerInfoById(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("organizations/{id}")
    Call<Organization> getOrganizationInfoById(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("organizations/{id}/signers")
    Call<Signers> getOrganizationSignersById(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("organizations/{id}/supervised")
    Call<Organizations> getOrganizationSupervisedById(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("organizations/{id}/units?descendants=children")
    Call<Units> getOrganizationUnitsById(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("units/{id}")
    Call<Unit> getUnitInfoById(@Path("id") String uid);

    @Headers({"Accept: application/json"})
    @GET("units/{id}/signers")
    Call<Signers> getUnitSignersById(@Path("id") String uid);

    /* Endpoints for Dictionaries */
    @Headers({"Accept: application/json"})
    @GET("dictionaries/{id}")
    Call<DictionaryItems> getDictionaryById(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("dictionaries")
    Call<Dictionaries> getDictionaries();

    @Headers({"Accept: application/json"})
    @GET("organizations")
    Call<Organizations> getOrganizations();

    @Headers({"Accept: application/json"})
    @GET("types")
    Call<DecisionTypes> getDecisionTypes();
}