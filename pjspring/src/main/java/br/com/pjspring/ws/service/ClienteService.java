package br.com.pjspring.ws.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.pjspring.ws.model.MsgJSON;
import com.google.gson.Gson;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Telephone;
import ezvcard.property.Url;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import br.com.pjspring.ws.model.Cliente;
import br.com.pjspring.ws.dao.ClienteDao;

@Service
public class ClienteService {

    @Autowired
    ClienteDao clienteDao;


    //Negocios
    public Cliente salvar(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    public Collection<Cliente> buscarTodos() {
        return clienteDao.findAll();
    }

    public void excluir(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    public Cliente buscarPorId(Integer id) {
        return clienteDao.getOne(id);
    }

    public void enviarWpp() {

        //https://app.chat-api.com/testing
        ArquivoTexto arquivoTexto = new ArquivoTexto();
        String str = arquivoTexto.dadosTexto();
        String menssagem = "Olaá testando com o sistema by Pedro Miguel, integração OK!";

        VCard vcard = Ezvcard.parse(str).first();
        String fullName = vcard.getFormattedName().getValue();
        String lastName = vcard.getStructuredName().getFamily();
        List<Telephone> telefones = vcard.getTelephoneNumbers();

        List<String> telefone = new ArrayList<>();
        telefone.add("+55 62 9-8407-0999");
        telefone.add("81883835");
        telefone.add("985621048");
        telefone.add("62985622444");
        telefone.add("01462996859795");

        for (String tel : telefone) {
            String resultado = removeAcentos(tel);

            if (resultado.length() == 8) {
                resultado = "55629" + resultado;
                System.out.println(resultado);
            } else if (resultado.length() == 9) {
                resultado = "5562" + resultado;
                System.out.println(resultado);
            } else if (resultado.length() == 11) {
                resultado = "55" + resultado;
                System.out.println(resultado);
            } else if (resultado.length() > 11) {
                resultado = resultado.substring(resultado.length() - 11);
                resultado = "55" + resultado;
                System.out.println(resultado);
            }

            URL url = null;
            try {
                url = new URL("https://eu38.chat-api.com/instance46126/sendMessage?token=600fuufitbbgv7zn");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

             Gson gson = new Gson();
             MsgJSON msgJSON = new MsgJSON();
             msgJSON.setPhone(resultado);
             msgJSON.setBody(menssagem);

             String jsonInputString = gson.toJson(msgJSON);
                     //"{\"phone\": \"5562984070999\", \"body\": \"teste\"}";

                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    public String removeAcentos(String textoNAOFormatado) {
        String resultado;
        resultado = textoNAOFormatado
                .replaceAll("[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/]", "")
                .replaceAll(" ", "");


        return resultado;
    }

}
