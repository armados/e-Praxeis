package com.armados.app.epraxeis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.armados.app.epraxeis.diaugeia.Decision;
import com.armados.app.epraxeis.diaugeia.DecisionSearchResult;
import com.armados.app.epraxeis.diaugeia.DecisionTypes;
import com.armados.app.epraxeis.diaugeia.Dictionaries;
import com.armados.app.epraxeis.diaugeia.DictionaryItems;
import com.armados.app.epraxeis.diaugeia.Organization;
import com.armados.app.epraxeis.diaugeia.Organizations;
import com.armados.app.epraxeis.diaugeia.Signer;
import com.armados.app.epraxeis.diaugeia.Signers;
import com.armados.app.epraxeis.diaugeia.Unit;
import com.armados.app.epraxeis.diaugeia.Units;
import com.armados.app.epraxeis.search.SearchQuery;

import retrofit2.Call;

public class Api {

    private static final String TAG = Api.class.getSimpleName();

    public static final ApiInterface service = RetrofitClientInstance
            .getRetrofitInstance()
            .create(ApiInterface.class);

    @Nullable
    public static DecisionSearchResult fetchDecisions(@NonNull final SearchQuery par) {
        Call<DecisionSearchResult> call = service
                .getDecisionsAdvanced(
                        par.getTerms().buildQuery(),
                        par.getPage(),
                        par.getPageSize()
                );
        RetrofitSyncCall<DecisionSearchResult> item = new RetrofitSyncCall<>(call);

        DecisionSearchResult result = item.execute();
        if (result == null) return null;

        for (int i = 0; i < result.getDecisions().size(); i++) {
            /* Δημιουργεί τη σχετική κλάση extra fields */
            result.getDecisions().get(i).processExtraFields();
        }

        return result;
    }

    @Nullable
    public static Decision fetchDecisionDetailsByAda(String ada) {
        Call<Decision> call = service.getDecisionByAda(ada);
        RetrofitSyncCall<Decision> item = new RetrofitSyncCall<>(call);

        Decision result = item.execute();
        if (result == null) return null;

        /* Δημιουργεί τη σχετική κλάση extra fields */
        result.processExtraFields();

        return result;
    }

    public static Organization fetchOrganizationInfoById(final String id) {
        Call<Organization> call = service.getOrganizationInfoById(id);
        RetrofitSyncCall<Organization> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Signers fetchOrganizationSignersById(final String id) {
        Call<Signers> call = service.getOrganizationSignersById(id);
        RetrofitSyncCall<Signers> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Organizations fetchOrganizationSupervisedById(final String id) {
        Call<Organizations> call = service.getOrganizationSupervisedById(id);
        RetrofitSyncCall<Organizations> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Units fetchOrganizationUnitsById(final String id) {
        Call<Units> call = service.getOrganizationUnitsById(id);
        RetrofitSyncCall<Units> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Signer fetchSignerInfoByUid(final String id) {
        Call<Signer> call = service.getSignerInfoById(id);
        RetrofitSyncCall<Signer> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Unit fetchUnitInfoByUid(final String id) {
        Call<Unit> call = service.getUnitInfoById(id);
        RetrofitSyncCall<Unit> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Signers fetchUnitSignersByUid(final String id) {
        Call<Signers> call = service.getUnitSignersById(id);
        RetrofitSyncCall<Signers> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Dictionaries fetchDictionaries() {
        Call<Dictionaries> call = service.getDictionaries();
        RetrofitSyncCall<Dictionaries> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static DictionaryItems fetchDictionaryItemsById(final String id) {
        Call<DictionaryItems> call = service.getDictionaryById(id);
        RetrofitSyncCall<DictionaryItems> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static Organizations fetchOrganizations() {
        Call<Organizations> call = service.getOrganizations();
        RetrofitSyncCall<Organizations> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }

    public static DecisionTypes fetchDecisionTypes() {
        Call<DecisionTypes> call = service.getDecisionTypes();
        RetrofitSyncCall<DecisionTypes> item = new RetrofitSyncCall<>(call);

        return item.execute();
    }
}
