package com.grupo6.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.rest.dto.EntradaHistorialDTO;
import com.grupo6.rest.dto.SuscripcionEspectaculoDTO;
import com.grupo6.service.EspectaculoService;
import com.grupo6.service.RealizacionEspectaculoService;
import com.grupo6.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    
    @Autowired
    private EspectaculoService espectaculoService;

    @Autowired
    private RealizacionEspectaculoService realizacionEspectaculoService;

    
    @RequestMapping(path = "/altaUsuarioFinal/", method = RequestMethod.PUT)
    public ResponseEntity<?> altaUsuarioGmailOpasswd(@RequestHeader("X-TenantID") String tenantName,
            @RequestBody Usuario dtos) {
        
        // si el campo gmail token está vacío lo usamos el password, sino el token
        TenantContext.setCurrentTenant(tenantName);
        Optional<Usuario> u = userService.altaUsuario(dtos);
        if (u.isPresent()){
            return new ResponseEntity<Object>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }
        
    }

    @RequestMapping(path = "/loginUsuarioFinal/", method = RequestMethod.GET)
    public ResponseEntity<?> loginUsuarioFinal(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "password", required = true) String password) {
      
        TenantContext.setCurrentTenant(tenantName);

        Optional<Usuario> usuario = userService.loginEmailPassword(email, password);
        if (usuario.isPresent()) {
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            if (usuario.get().getPassword().equals(sha256hex)) {
                
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                
                request.getSession().setAttribute("username", usuario.get().getNombre() + ' ' + usuario.get().getApellido());
                return new ResponseEntity<Object>(HttpStatus.OK);
                
                
            }
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(path = "/loginUsuarioFinalGmail/", method = RequestMethod.POST)
    public ResponseEntity<?> loginUsuarioFinalGmail(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "id", required = false) String id) {
        System.out.println("loginUsuarioFInalGmail");
        System.out.println("email: " + email);
        System.out.println("id: " + id);

        TenantContext.setCurrentTenant(tenantName);
        Optional<Usuario> usuario = userService.altaOLoginConGmail(id, email);
        if (usuario.isPresent()) {
            System.
 out.println("usuario.isPresent()");
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);

                request.getSession().setAttribute("username", usuario.get().getEmail());
                return new ResponseEntity<Object>(HttpStatus.OK);
                
        }
        System.out.println("FORBIDDEN");
        return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
    }
    
    @RequestMapping(path = "/consultaHistorialCompras/", method = RequestMethod.POST)
    public ResponseEntity<List<EntradaHistorialDTO>> consultaHistorialCompras(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = false) String email) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> a = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
            return new ResponseEntity<List<EntradaHistorialDTO>>(HttpStatus.FORBIDDEN);
        }else {
            List<EntradaHistorialDTO> historial = userService.consultaHistorialCompras(email);
            return new ResponseEntity<List<EntradaHistorialDTO>>(historial,HttpStatus.OK);
        }
    }
    
    /*
     * suscribirse y dessuscribirse de especataculos tipos de espectaculo y
     * realizaciones de espectaculo
     */

    @RequestMapping(path = "/suscribirseEspectaculo/", method = RequestMethod.GET)
    public ResponseEntity<?> suscribirseEEspectaculo(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idEspectaculo", required = true) Long idEspectaculo) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        espectaculoService.suscribirseAEspectaculo(idEspectaculo, email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(path = "/desSuscribirseEspectaculo/", method = RequestMethod.GET)
    public ResponseEntity<?> desSuscribirseEntradaEspectaculo(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idEspectaculo", required = true) Long idEspectaculo) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        espectaculoService.desSuscribirseAEspectaculo(idEspectaculo, email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(path = "/suscribirseTipoEspectaculo/", method = RequestMethod.GET)
    public ResponseEntity<?> suscribirseTipoEspectaculo(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idTipoEspectaculo", required = true) Long idTipoEspectaculo) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        espectaculoService.suscribirseTipoEspectaculo(idTipoEspectaculo, email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(path = "/desSuscribirseTipoEspectaculo/", method = RequestMethod.GET)
    public ResponseEntity<?> desSuscribirseTipoEspectaculo(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idTipoEspectaculo", required = true) Long idTipoEspectaculo) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        espectaculoService.desSuscribirseTipoEspectaculo(idTipoEspectaculo, email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(path = "/suscribirserealizacionEspectaculo/", method = RequestMethod.GET)
    public ResponseEntity<?> suscribirserealizacionEspectaculo(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idRealizacionEspectaculo", required = true) Long idRealizacionEspectaculo) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        realizacionEspectaculoService.suscribirse(idRealizacionEspectaculo, email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(path = "/desSuscribirseRealizacionEspectaculo/", method = RequestMethod.GET)
    public ResponseEntity<?> desSuscribirseRealizacionEspectaculo(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idTrealizacionEspectaculo", required = true) Long idTrealizacionEspectaculo) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        realizacionEspectaculoService.desSuscribirse(idTrealizacionEspectaculo, email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(path = "/verSuscripcionesUsuario/", method = RequestMethod.GET)
    public ResponseEntity<List<SuscripcionEspectaculoDTO>> verSuscripcionesUsuario(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request, @RequestParam(name = "email", required = true) String email) {

        TenantContext.setCurrentTenant(tenantName);
        @SuppressWarnings("unchecked")
        Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
        if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
            return new ResponseEntity<List<SuscripcionEspectaculoDTO>>(HttpStatus.FORBIDDEN);
        }
        List<SuscripcionEspectaculoDTO> suL = realizacionEspectaculoService.verSuscripcionUsuario(email);
        return new ResponseEntity<List<SuscripcionEspectaculoDTO>>(suL, HttpStatus.OK);
    }
    @RequestMapping(path = "/cerrarSesionUsuario/", method = RequestMethod.GET)
    public ResponseEntity<?> cerrarSesionUsuario(@RequestHeader("X-TenantID") String tenantName,
            HttpServletRequest request) {
        
        request.getSession().removeAttribute("usuario");
        request.getSession().invalidate();
        
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}