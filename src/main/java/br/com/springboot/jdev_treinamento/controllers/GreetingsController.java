package br.com.springboot.jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.jdev_treinamento.model.Usuario;
import br.com.springboot.jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /*IC/CD ou CDI - Injeção de dependencias*/
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Teste Sprig Boot API: " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);/*Grava no banco de dados*/
    	
    	return "Ola Mundo" + nome;
    }
    
    @GetMapping(value = "listatodos")/*Nosso primeiro método de API*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*Retorna a lista em JSON*/
    	
    }

    @PostMapping(value = "salvar")/*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "delete")/*mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete(@RequestParam long iduser){/*Recebe o dado para ser deletado*/
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    	
    }
    
    
    
}
