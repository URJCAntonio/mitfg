package com.tfg.trabajoAnto.componentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tfg.trabajoAnto.modelo.Administrador;
import com.tfg.trabajoAnto.modelo.Alumno;
import com.tfg.trabajoAnto.modelo.Aula;
import com.tfg.trabajoAnto.modelo.ModeloEjercicio;
import com.tfg.trabajoAnto.modelo.Complejo;
import com.tfg.trabajoAnto.modelo.Medio;
import com.tfg.trabajoAnto.modelo.ModeloExamen;
import com.tfg.trabajoAnto.modelo.Simple;
import com.tfg.trabajoAnto.modelo.Profesor;
import com.tfg.trabajoAnto.repositorio.RepoAula;
import com.tfg.trabajoAnto.repositorio.RepoModeloEjercicio;
import com.tfg.trabajoAnto.repositorio.RepoModeloExamen;
import com.tfg.trabajoAnto.servicio.ServicioUsuario;

@Component
public class DataLoader {

	/*
    private RepoRol roles;
    */
    private ServicioUsuario usuarios;
    
    private RepoAula repoAulas;
    private RepoModeloEjercicio repoEjercicio;
    private RepoModeloExamen repoModelo;

    @Autowired/*2323*/
    public DataLoader(/*RepoRol rolRepository,*/ ServicioUsuario usuarios, RepoAula repoAulas, RepoModeloEjercicio repoEjercicio, RepoModeloExamen repoModelo) {
        //this.roles = rolRepository;
        this.usuarios = usuarios;
        this.repoAulas = repoAulas;
        this.repoEjercicio = repoEjercicio;
        this.repoModelo = repoModelo;
        
        Administrador admin = new Administrador();
    	admin.setNombre("AdministradorSupremo");
    	admin.setContra("kjds983njsd90u3h8dsf0");
    	admin.setInstitucion("URJC");
        usuarios.registrarUsuario(admin);
        
        //LoadRoles();
        //LoadUsers();
        //LoadAulas();
        InicializarEjercicios();
        //Pruebas_de_campo();
        //InicializarExamen();
    }
/*
    private void LoadRoles() {
    	Rol rol = new Rol();
    	
    	rol.setId(1);
    	rol.setNombre("ALUMNOS");
    	roles.save(rol);
    	
    	rol.setId(2);
    	rol.setNombre("PROFESORES");
    	roles.save(rol);
    	
    	rol.setId(3);
    	rol.setNombre("ADMINISTRADOR");
    	roles.save(rol);
    }
    */
    private void LoadUsers() {
    	
    	Administrador admin = new Administrador();
    	admin.setNombre("AdministradorSupremo");
    	admin.setContra("kjds983njsd90u3h8dsf0");
    	admin.setInstitucion("URJC");
        usuarios.registrarUsuario(admin);
        
    	
    	Profesor profe = new Profesor();
        profe.setNombre("pro");
        profe.setContra("1234");
        profe.setInstitucion("URJC");
        usuarios.registrarUsuario(profe);
        
        Alumno alumno = new Alumno();
        alumno.setNombre("al");
        alumno.setContra("1234");
        alumno.setInstitucion("URJC");
        usuarios.registrarUsuario(alumno);
    }
    
    private void LoadAulas() {
    	Aula aula = new Aula();
    	aula.setNombre("Primero");
    	aula.setCursoAcademico("22-23");
    	aula.setAsignatura("Tecnología");
    	repoAulas.save(aula);
    }
    
    private void Pruebas_de_campo() {
    	
    	//crear aulas
    	Aula a;
    	
    	a = new Aula();
    	a.setNombre("Segundo");
    	a.setCursoAcademico("22-23");
    	a.setAsignatura("Tecnología");
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a.setNombre("Tercero");
    	a.setCursoAcademico("22-23");
    	a.setAsignatura("Tecnología");
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a.setNombre("Cuarto");
    	a.setCursoAcademico("22-23");
    	a.setAsignatura("Tecnología");
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a.setNombre("Quinto");
    	a.setCursoAcademico("22-23");
    	a.setAsignatura("Tecnología");
    	repoAulas.save(a);
    	
    	//crear alumnos
    	Alumno al = new Alumno();
    	
    	al.setNombre("alP1");
    	al.setContra("1234");
    	al.setInstitucion("URJC");
        usuarios.registrarUsuario(al);
        
        al = new Alumno();
        al.setNombre("alS1");
    	al.setContra("1234");
    	al.setInstitucion("URJC");
        usuarios.registrarUsuario(al);

        al = new Alumno();
        al.setNombre("alS2");
    	al.setContra("1234");
    	al.setInstitucion("URJC");
        usuarios.registrarUsuario(al);

        al = new Alumno();
        al.setNombre("alS3");
    	al.setContra("1234");
    	al.setInstitucion("URJC");
        usuarios.registrarUsuario(al);

        al = new Alumno();
        al.setNombre("alT1");
    	al.setContra("1234");
    	al.setInstitucion("URJC");
        usuarios.registrarUsuario(al);

        al = new Alumno();
        al.setNombre("alT2");
    	al.setContra("1234");
    	al.setInstitucion("URJC");
        usuarios.registrarUsuario(al);
        
        
    	//inicializar alumno y profesor
        al = new Alumno();
    	Profesor p = (Profesor) usuarios.getUsuarioPorNombre("pro");
    	al = (Alumno) usuarios.getUsuarioPorNombre("al");
    	
    	
    	//Añadir aulas a profesores
    	a = new Aula();
    	a = repoAulas.getAulaById(1);
    	p.getAula().add(a);
    	a = new Aula();
    	a = repoAulas.getAulaById(2);
    	p.getAula().add(a);
    	a = new Aula();
    	a = repoAulas.getAulaById(3);
    	p.getAula().add(a);
    	usuarios.save(p);
    	
    	//Añadir alumnos a las aulas
    	a = repoAulas.getAulaById(1);
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a = repoAulas.getAulaById(2);
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a = repoAulas.getAulaById(3);
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a = repoAulas.getAulaById(1);
    	al = (Alumno) usuarios.getUsuarioPorNombre("alP1");
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a = repoAulas.getAulaById(2);
    	al = (Alumno) usuarios.getUsuarioPorNombre("alS1");
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a = repoAulas.getAulaById(2);
    	al = (Alumno) usuarios.getUsuarioPorNombre("alS2");
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    	
    	a = new Aula();
    	a = repoAulas.getAulaById(2);
    	al = (Alumno) usuarios.getUsuarioPorNombre("alS3");
    	a.getAlumnos().add(al);
    	repoAulas.save(a);

    	a = new Aula();
    	a = repoAulas.getAulaById(3);
    	al = (Alumno) usuarios.getUsuarioPorNombre("alT1");
    	a.getAlumnos().add(al);
    	repoAulas.save(a);

    	a = new Aula();
    	a = repoAulas.getAulaById(3);
    	al = (Alumno) usuarios.getUsuarioPorNombre("alT2");
    	a.getAlumnos().add(al);
    	repoAulas.save(a);
    }
    
    private void InicializarEjercicios() {

		//EJERCICIO SIMPLE
    	ModeloEjercicio ej = new Simple();
    	ej.setFuncion_logica("S = !a");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997528918514995210/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = a * b");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997527125382934538/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = a + b");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997527313015115827/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = !(a * b)");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997527915296206878/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = !(a + b)");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997528170100170782/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = a * !b");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997513451477934170/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = !a + b");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997514521994346496/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = a * (!a + b)");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997516312253321277/unknown.png");
    	repoEjercicio.save(ej);
    	ej = new Simple();
    	ej.setFuncion_logica("S = a * (a + b)");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997516627501404251/unknown.png");
    	repoEjercicio.save(ej);

		//EJERCICIO MEDIO
    	ej = new Medio();
    	ej.setFuncion_logica("S = c * a + b");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997158791512604764/unknown.png");
    	repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = a + b * c");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997157045398683688/unknown.png");
		repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = (!a + !c) * b");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997160525353975908/unknown.png");
		repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = !(a * b) + c");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997517828431298610/unknown.png");
		repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = !(a + c) + (a * b)");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997519185146019971/unknown.png");
		repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = (b * c) + !(a * c)");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997521250299682846/unknown.png");
		repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = !(a + b) * !(a * c)");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997525456347738122/unknown.png");
		repoEjercicio.save(ej);
		ej = new Medio();
		ej.setFuncion_logica("S = !(a + c)* b");
		ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997525984360280165/unknown.png");
		repoEjercicio.save(ej);
		
		//EJERCICIO COMPLEJO
		ej = new Complejo();
		((Complejo) ej).setFunciones_logicas("D = a*b", "E = !b", "F = !(a*b*!b)", "G = !(a*b+!(a*b*!b))", "H = !b+c", "S = !(a*b+!(a*b*!b))*(!b+c)");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997157079167021187/unknown.png");
    	repoEjercicio.save(ej);
		ej = new Complejo();
		((Complejo) ej).setFunciones_logicas("D = !b", "E = a+!b", "F = !(a+!b)", "G = !b", "H = !b*c", "S = !(a+!b)+!b*c");
    	ej.setImagen("https://cdn.discordapp.com/attachments/233960589615955970/997157100738318347/unknown.png");
    	repoEjercicio.save(ej);
    }
    
    private void InicializarExamen() {
    	ModeloExamen modeloExamen = new ModeloExamen();
    	modeloExamen.setNombre("hola");
    	modeloExamen.getNoImg().add(repoEjercicio.getEjercicioById(1));

    	/*
    	modeloExamen.getSiImg().add(repoEjercicio.getEjercicioById(1));
    	modeloExamen.getNoImg().add(repoEjercicio.getEjercicioById(19));
    	modeloExamen.getSiImg().add(repoEjercicio.getEjercicioById(19));
    	modeloExamen.getTablaVerdad().add(repoEjercicio.getEjercicioById(19));
    	modeloExamen.getSiImg().add(repoEjercicio.getEjercicioById(35));
    	*/
    	repoModelo.save(modeloExamen);
    }
}
