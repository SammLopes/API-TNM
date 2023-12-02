package com.appmain.appmain.config;


import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.PlatformTransactionManager;
import com.appmain.appmain.model.Usuarios;
import com.appmain.appmain.model.repositorys.UsuariosRepository;
import com.appmain.appmain.service.UsuarioService;
import com.appmain.appmain.service.auth.AutFilterAdmin;
import com.appmain.appmain.util.JWTUtil;

//@Configuration
public class AppConfig implements CommandLineRunner {


    @Autowired
    private AutFilterAdmin authFilterAdmin;

    @Autowired
    private PlatformTransactionManager transactionManager;
    //repositório de usuários
    @Autowired
    UsuariosRepository usuariosRepository;

    
    @Autowired
    private JWTUtil jwtUtil;


    @Bean
    public UsuarioService usuarioService() {
        return new UsuarioService();
    }


     @Bean
    public FilterRegistrationBean<AutFilterAdmin> filterRegistrationBeanAdmin() {
    FilterRegistrationBean<AutFilterAdmin> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(authFilterAdmin);
    //aplica-se apenas ao endpoint admin
    registrationBean.addUrlPatterns("/admin/*");
    //define a ordem de precedencia do filtro
    registrationBean.setOrder(1);
    return registrationBean;
    }
 
    /**
     * método responsável por inserir usuários iniciais no banco
    */
    @Transactional
    private void addUsers() {
    //pesquisa -> https://www.baeldung.com/spring-programmatic-transaction-management
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
    definition.setTimeout(-1);

    TransactionStatus status = transactionManager.getTransaction(definition);

    try {
    //(data,email,login,nome,permissoes,senha,telefone,usuario_id
    Usuarios u = new Usuarios();
    u.setData(LocalDateTime.now());
    u.setTelefone("9999999999");
    u.setEmail("caracas");
    u.setNome("Jude Belligham");
    u.setLogin("admin");
    u.setSenha("1234");
    u.setPermissoes("ADMIN,USER");
    usuariosRepository.save(u);

    /*
     * u = new Usuarios();
    u.setLogin("usuario1");
    u.setSenha("1234");
    u.setPermissoes("USER,TAREFA");
    usuariosRepository.save(u);

    u = new Usuarios();
    u.setLogin("usuario2");
    u.setSenha("1234");
    u.setPermissoes("TAREFA");
    usuariosRepository.save(u);
     * 
     */
    

    transactionManager.commit(status);

    } catch (Exception ex) {
    transactionManager.rollback(status);
    }
    }

    /**
     * método executado no momento da execução da aplicação
    * 
    * @param args
    * @throws Exception 
    */
    @Override
    public void run(String... args) throws Exception {
        addUsers();
    }
}
