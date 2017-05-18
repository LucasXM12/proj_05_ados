package com.ados.cotuca.proj_05_ados.tools;

import org.json.*;
import android.support.annotation.*;

public class Utils {

    public Disciplina getInformacao(@NonNull String end) throws Exception {
        Disciplina ret;

        String json = NetworkUtils.getJSONFromAPI(end);
        ret = parseJson(json);

        return ret;
    }

    private Disciplina parseJson(String json) throws Exception {
        try {
            JSONObject jsonObj = new JSONObject(json);

            int cod = jsonObj.getInt("codigo");
            String materia = jsonObj.getString("nome");

            return new Disciplina(cod, materia);
        } catch (JSONException e) {
            throw e;
        }
    }
}
