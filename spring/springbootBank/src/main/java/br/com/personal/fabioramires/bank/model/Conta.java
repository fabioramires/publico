package br.com.personal.fabioramires.bank.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Conta implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idConta;
    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;
    @JsonIgnore
    @OneToMany(mappedBy="conta")
    private List<Movimentacao> transacoes;
    @Column(nullable= false)
    private double saldo;
    @Column
    private double limiteSaqueDiario;
    @Column
    private boolean flagAtivo;
    @Column
    private String tipo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dataCriacao;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        if (idConta == null) {
            if (other.idConta != null)
                return false;
        } else if (!idConta.equals(other.idConta))
            return false;
        return true;
    }


}