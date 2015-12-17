package br.com.caelum.geradordeprovas.interceptors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.geradordeprovas.models.Usuario;


public class AutorizadorInterceptorTest {
	
	private static final String USUARIO_LOGADO = "usuario";

	private AutorizadorInterceptor interceptor;
	
	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;
	@Mock private HttpSession session;
	
	@Before
	public void init() { 
		interceptor = new AutorizadorInterceptor();
		
		MockitoAnnotations.initMocks(this);
		when(request.getSession()).thenReturn(session);
	}
	
	@Test
	public void deve_retornar_true_ao_acessar_alguma_url_de_uma_parte_nao_restrita() throws Exception {
		when(request.getRequestURI()).thenReturn("uriMarota/loginForm");
		
		assertTrue(interceptor.preHandle(request, response, null));
	}
	
	@Test
	public void deve_retornar_true_se_tiver_logado() throws Exception {
		Usuario usuario = new Usuario();
		
		when(request.getRequestURI()).thenReturn("/uri_ultra_segura_dos_paranaue");
		when(request.getSession().getAttribute(USUARIO_LOGADO)).thenReturn(usuario);
		
		assertTrue(interceptor.preHandle(request, response, null));
	}
	
	@Test
	public void deve_retornar_false_se_usuario_nao_for_admin_e_tentar_acessar_alguma_uri_de_admin() throws Exception{ 
		Usuario usuario = new Usuario();
		
		when(request.getRequestURI()).thenReturn("/admin");
		when(request.getSession().getAttribute(USUARIO_LOGADO)).thenReturn(usuario);
		
		assertFalse(interceptor.preHandle(request, response, null));
	}
	
	@Test
	public void deve_retornar_false_se_nao_tiver_usuario_logado() throws Exception { 
		when(request.getRequestURI()).thenReturn("/uri_super_marota_seguragem_total");
		when(request.getSession().getAttribute(USUARIO_LOGADO)).thenReturn(null);
		
		assertFalse(interceptor.preHandle(request, response, null));
	}
	
	@Test
	public void deve_redirecionar_caso_nao_esteja_autorizado() throws Exception { 
		when(request.getRequestURI()).thenReturn("/uri_super_marota_seguragem_total");
		
		interceptor.preHandle(request, response, null);
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		verify(response, times(1)).sendRedirect(argumentCaptor.capture());
	}

}
