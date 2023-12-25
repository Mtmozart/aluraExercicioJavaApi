import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Address;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import models.AdressViaCep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int cep = 0;
        List<Address> address = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while (cep != 1){
            System.out.print("Digite o cep de busca, como no exemplo 12345678: ");
            cep = sc.nextInt();
            if(cep == 1){
                break;
            }

            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                AdressViaCep adressViaCep = gson.fromJson(json.toString(), AdressViaCep.class);
                System.out.println(adressViaCep);

                Address buscaEndereco = new Address(adressViaCep);

                address.add(buscaEndereco);
            }
            catch (Exception e){
                System.out.println("Erro inesperado: " + e.getMessage());
            }


        }


        FileWriter file = new FileWriter("address.json");
        file.write(gson.toJson(address));
        file.close();

        System.out.println("Programa finalizado com sucesso");
    sc.close();
    }
}