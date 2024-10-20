package br.com.alura.screenmatch;
import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ScreenmatchApplicationTests implements CommandLineRunner {

	@Test
	void contextLoads() throws Exception {
		run();
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=Stranger+Things&apikey=e0dc7e17");
		System.out.println(json);
//		json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=Stranger+Things&season=1&episode=2&apikey=e0dc7e17");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json,DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<= dados.totalTemporadas(); i++){
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=Stranger+Things&season=" + i +"&apikey=e0dc7e17");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);


	}
}
