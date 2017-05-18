package com.ados.cotuca.proj_05_ados.tools;

import java.io.*;
import java.net.*;

import android.support.annotation.*;

public class NetworkUtils {

    //Responsavel por carregar o Objeto JSON
    public static String getJSONFromAPI(String url) throws Exception {
        String ret = null;

        try {
            int respCod;
            URL apiEnd = new URL(url);

            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");

            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);

            conexao.connect();

            respCod = conexao.getResponseCode();

            if (respCod < HttpURLConnection.HTTP_BAD_REQUEST)
                is = conexao.getInputStream();
            else
                is = conexao.getErrorStream();

            ret = inputStreamToString(is);

            is.close();
            conexao.disconnect();
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        return ret;
    }

    @NonNull
    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuffer buffer = new StringBuffer();

        try {
            String linha;
            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(is));

            while ((linha = br.readLine()) != null)
                buffer.append(linha);

            br.close();
        } catch (IOException e) {
            throw e;
        }

        return buffer.toString();
    }
}

