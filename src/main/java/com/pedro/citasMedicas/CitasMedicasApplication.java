package com.pedro.citasMedicas;

import com.pedro.citasMedicas.model.*;
import com.pedro.citasMedicas.service.MedicoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CitasMedicasApplication {

	public static void main(String[] args) {

		SpringApplication.run(CitasMedicasApplication.class, args);
		/*List<Paciente> pacientes= new ArrayList<Paciente>();
		Paciente p1=new Paciente();
		p1.setNombre("Pedro");
		Paciente p2=new Paciente();
		p2.setNombre("Javi");
		Paciente p3=new Paciente();
		p3.setNombre("Agustin");
		pacientes.add(p1);
		pacientes.add(p2);
		pacientes.add(p3);
		System.out.println(pacientes);

		List<Medico> medicos= new ArrayList<Medico>();
		Medico m1=new Medico();
		m1.setNombre("Pedro");
		m1.setPacientes(pacientes);
		Medico m2=new Medico();
		m2.setNombre("Javi");
		Medico m3=new Medico();
		m3.setNombre("Agustin");
		medicos.add(m1);
		medicos.add(m2);
		medicos.add(m3);

		System.out.println(medicos);
		Cita c1=new Cita();
		c1.setId(1L);
		c1.setMotivoCita("Por perder tiempo");
		c1.setPaciente(p1);
		c1.setMedico(new Medico());
		System.out.println(c1.toString());*/
	}

}
