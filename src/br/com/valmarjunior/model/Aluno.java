package br.com.valmarjunior.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nome;
	
	private String curso;
	
	private int matricula;
	
	private int semestre;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Aluno() {
	}
	
	public Aluno(String nome, String curso, int matricula, int semestre, Status status) {
		this.nome = nome;
		this.curso = curso;
		this.matricula = matricula;
		this.semestre = semestre;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public int getSemestre() {
		return semestre;
	}
	
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", curso=" + curso + ", matricula=" + matricula + ",semestre=" + semestre + ",status=" + status + "]";
	}
	
}
