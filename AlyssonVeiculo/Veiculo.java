package AlyssonVeiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Veiculo {
    private int id;
    private String numeroChassi;
    private String placa;
    private String modelo;
    private String nome;
    private double valor;

    public Veiculo(String numeroChassi, String placa, String modelo, String nome, double valor) {
        this.numeroChassi = numeroChassi;
        this.placa = placa;
        this.modelo = modelo;
        this.nome = nome;
        this.valor = valor;
    }

    public Veiculo() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Veiculo [id=" + id + ", numeroChassi=" + numeroChassi + ", placa=" + placa + ", modelo=" + modelo
                + ", nome=" + nome + ", valor=" + valor + "]";
    }
}

