package com.tfg.trabajoAnto.servicio;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfg.trabajoAnto.modelo.Administrador;
import com.tfg.trabajoAnto.modelo.Alumno;
import com.tfg.trabajoAnto.modelo.Aula;
import com.tfg.trabajoAnto.modelo.Complejo;
import com.tfg.trabajoAnto.modelo.DetallesUsuario;
import com.tfg.trabajoAnto.modelo.Ejercicio;
import com.tfg.trabajoAnto.modelo.Medio;
import com.tfg.trabajoAnto.modelo.ModeloEjercicio;
import com.tfg.trabajoAnto.modelo.Profesor;
import com.tfg.trabajoAnto.modelo.Simple;
import com.tfg.trabajoAnto.modelo.Usuario;
import com.tfg.trabajoAnto.repositorio.RepoAula;
import com.tfg.trabajoAnto.repositorio.RepoEjercicio;
import com.tfg.trabajoAnto.repositorio.RepoModeloEjercicio;
import com.tfg.trabajoAnto.repositorio.RepoModeloExamen;
import com.tfg.trabajoAnto.repositorio.RepoUsuario;


@Service
public class ServicioUsuario implements UserDetailsService{
	
	@Autowired
	private RepoUsuario repousu;
	
	@Autowired
	private RepoAula repoaula;
	
	@Autowired
	private RepoEjercicio repoej;
	
	@Autowired
	private RepoModeloEjercicio repoModEj;
	
	@Autowired
	private RepoModeloExamen repoex;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario user = repousu.getUserByUsername(username);
        
		if (user == null) {
			throw new UsernameNotFoundException("No se pudo encontrar al usuario" + username);
        }
        return new DetallesUsuario(user);
	}
	
	public Usuario getUsuarioPorId(int id) {
		Usuario user = repousu.getUserById(id);
		return user;
	}
	
	public Usuario getUsuarioPorNombre(String usu) {
		Usuario user = repousu.getUserByUsername(usu);
		return user;
	}
	
	public List<Usuario> getAllUsers() {
		List<Usuario> users = repousu.getAllUsers();
		return users;
	}
	
	public List<Alumno> getAllAlumnos() {
		List<Alumno> users = repousu.getAllAlumnos();
		return users;
	}
	public List<Profesor> getAllProfesores() {
		List<Profesor> users = repousu.getAllProfesores();
		return users;
	}

	public List<Integer> getExamenesByUserId(int i){
		List<Integer> examenes = repoex.getExamenesByUserId(i);
		return examenes;
		
	}
	
	public String checkUserExam(int alumno, int examen){
		return repoex.checkUserExam(alumno, examen);
		
	}
	
	public void registrarUsuario(Alumno alumno) {
		String cont=alumno.getContra();
		String contCodificada=encoder.encode(cont);
		alumno.setContra(contCodificada);
		alumno.setEnabled(true);	//activa la cuenta
		repousu.save(alumno);
	}
	
	public void registrarUsuario(Profesor profesor) {
		String cont=profesor.getContra();
		String contCodificada=encoder.encode(cont);
		profesor.setContra(contCodificada);
		profesor.setEnabled(true);	//activa la cuenta
		repousu.save(profesor);
	}
	
	public void registrarUsuario(Administrador administrador) {
		String cont=administrador.getContra();
		String contCodificada=encoder.encode(cont);
		administrador.setContra(contCodificada);
		administrador.setEnabled(true);	//activa la cuenta
		repousu.save(administrador);
	}

	//USUARIOS
	public List<Usuario> getUsuariosbyAulaId(int id) {
		return repousu.getUsuariosbyAulaId(id);
	}
	
	public List<Alumno> getAlumnosbyAula(Aula aula) {
		return repousu.getAlumnosbyAulaId(aula.getId());
	}
	
	public void banUsuarioByNombre(String usuario) {
		repousu.banUsuarioByNombre(usuario);
	}
	
	public void cambiarContra(Alumno alumno, String contra) {
		String contCodificada=encoder.encode(contra);
		alumno.setContra(contCodificada);
		repousu.save(alumno);
	}
	
	public boolean cambiarMiContra(Alumno alumno, String newContra, String contra) {
		
		if(encoder.matches(contra, alumno.getContra())) {
			alumno.setContra(encoder.encode(newContra));
			repousu.save(alumno);
			return true;
		}
		return false;
	}
	
	public HashMap<Simple, String> getAllSimple() {
		HashMap<Simple, String> mapa = new HashMap<>();
		List<Integer> idEjercicios = repoej.getIdEjerciciosSimples();
		int n;
		int d;
		for (Integer integer : idEjercicios) {
			n=0; d=0;
			ModeloEjercicio ej = repoModEj.getEjercicioById(integer);
			List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(integer);
			for (Ejercicio ejercicio : estadisticas) {
				boolean[] correctos = ejercicio.getCorrecto();
				for (boolean c : correctos) {
					if(c)n++;
					d++;
				}
			}
			mapa.put((Simple) ej, n+"/"+d);
		}
		return mapa;
	}

	public HashMap<Medio, String> getAllMedio() {
		HashMap<Medio, String> mapa = new HashMap<>();
		List<Integer> idEjercicios = repoej.getIdEjerciciosMedios();
		int n;
		int d;
		for (Integer integer : idEjercicios) {
			n=0; d=0;
			ModeloEjercicio ej = repoModEj.getEjercicioById(integer);
			List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(integer);
			for (Ejercicio ejercicio : estadisticas) {
				boolean[] correctos = ejercicio.getCorrecto();
				for (boolean c : correctos) {
					if(c)n++;
					d++;
				}
			}
			mapa.put((Medio) ej, n+"/"+d);
		}
		return mapa;
	}

	public HashMap<Complejo, String> getAllComplejo() {
		HashMap<Complejo, String> mapa = new HashMap<>();
		List<Integer> idEjercicios = repoej.getIdEjerciciosComplejos();
		int n;
		int d;
		for (Integer integer : idEjercicios) {
			n=0; d=0;
			ModeloEjercicio ej = repoModEj.getEjercicioById(integer);
			List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(integer);
			for (Ejercicio ejercicio : estadisticas) {
				boolean[] correctos = ejercicio.getCorrecto();
				for (boolean c : correctos) {
					if(c)n++;
					d++;
				}
			}
			mapa.put((Complejo) ej, n+"/"+d);
		}
		return mapa;
	}
	
	public HashMap<Simple, String> getSimpleById(int[] id) {
		HashMap<Simple, String> mapa = new HashMap<>();
		List<Integer> idEjercicios = repoej.getIdEjerciciosSimples(id);
		int n;
		int d;
		for (Integer integer : idEjercicios) {
			n=0; d=0;
			ModeloEjercicio ej = repoModEj.getEjercicioById(integer);
			List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(id, integer);
			for (Ejercicio ejercicio : estadisticas) {
				boolean[] correctos = ejercicio.getCorrecto();
				for (boolean c : correctos) {
					if(c)n++;
					d++;
				}
			}
			mapa.put((Simple) ej, n+"/"+d);
		}
		return mapa;
	}
	
	public HashMap<Medio, String> getMedioById(int[] id) {
		HashMap<Medio, String> mapa = new HashMap<>();
		List<Integer> idEjercicios = repoej.getIdEjerciciosMedios(id);
		int n;
		int d;
		for (Integer integer : idEjercicios) {
			n=0; d=0;
			ModeloEjercicio ej = repoModEj.getEjercicioById(integer);
			List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(id, integer);
			for (Ejercicio ejercicio : estadisticas) {
				boolean[] correctos = ejercicio.getCorrecto();
				for (boolean c : correctos) {
					if(c)n++;
					d++;
				}
			}
			mapa.put((Medio) ej, n+"/"+d);
		}
		return mapa;
	}
	
	public HashMap<Complejo, String> getComplejoById(int[] id) {
		HashMap<Complejo, String> mapa = new HashMap<>();
		List<Integer> idEjercicios = repoej.getIdEjerciciosComplejos(id);
		int n;
		int d;
		for (Integer integer : idEjercicios) {
			n=0; d=0;
			ModeloEjercicio ej = repoModEj.getEjercicioById(integer);
			List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(id, integer);
			for (Ejercicio ejercicio : estadisticas) {
				boolean[] correctos = ejercicio.getCorrecto();
				for (boolean c : correctos) {
					if(c)n++;
					d++;
				}
			}
			mapa.put((Complejo) ej, n+"/"+d);
		}
		return mapa;
	}
	
	public HashMap<Alumno, Float> rankear(Alumno[] alumnos) {
		HashMap<Alumno, Float> mapa = new HashMap<>();
		for (Alumno alumno : alumnos) {
			int i[] = {alumno.getId()};
			List<Integer> idEjercicios = repoej.getIdEjerciciosSimples(i);
			int n=0;
			int d=0;
			boolean dividir=false;
			for (Integer integer : idEjercicios) {
				dividir = true;
				List<Ejercicio> estadisticas = repoej.getCorrectoIncorrecto(i[0], integer);
				for (Ejercicio ejercicio : estadisticas) {
					boolean[] correctos = ejercicio.getCorrecto();
					for (boolean c : correctos) {
						if(c)n++;
						d++;
					}
				}
			}
			if(dividir) {
				mapa.put(alumno, (float)n/(float)d);
			}
		}
		return mapa;
	}
	
	//AULAS
	public List<Aula> getAllAulas() {
		return repoaula.getAllAulas();
	}
	
	public List<Aula> getAulasByProfesor(Profesor profesor) {
		return repoaula.getAulasByProfesorId(profesor.getId());
	}
	
	public List<Aula> getNuevasAulasProfesorId(Profesor profesor) {
		return repoaula.getNuevasAulasProfesorId(profesor.getId());
	}
	
	public Aula getAulaByNombre(String nombre) {
		return repoaula.getAulaByNombre(nombre);
	}
	
	
	public void crearAula(String aula, String[] alumnos, String curso, String asignatura) {
		Aula au = new Aula();
    	au.setNombre(aula);
    	System.out.println("aula "+aula);
    	au.setCursoAcademico(curso);
    	System.out.println("curso "+curso);
    	au.setAsignatura(asignatura);
    	System.out.println("asignatura "+asignatura);
    	
    	for (String alumno : alumnos) {
    		au.getAlumnos().add((Alumno) repousu.getUserByUsername(alumno));
		}
        repoaula.save(au);
	}
	
	//EJERCICIOS
	
	//SAVE
	public void save(Usuario usuario) {
		repousu.save(usuario);
	}
}
