package controller;

import java.math.BigDecimal;
import java.util.List;

import entity.Autor;
import entity.Editora;
import entity.Livro;
import entity.TipoPublicacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public final class App {

    public static void main(String[] args) {

        System.out.println("\n*** Aula 2 — JPA/Hibernate com PostgreSQL ***\n");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("academico");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Editora editora = new Editora(null, "Novatec", "São Paulo");

        Autor autor = new Autor(null, "Martin Fowler", "Britânica");

        Livro livro = new Livro(
            null,
            "Padrões de Arquitetura de Aplicações Corporativas",
            2002,
            "978-0-321-12521-7",
            new BigDecimal("149.90"),
            TipoPublicacao.IMPRESSO
        );

        livro.setEditora(editora);

        livro.adicionarAutor(autor);

        em.persist(editora);
        em.persist(autor);
        em.persist(livro);

        em.getTransaction().commit();

        System.out.println("✔ Dados persistidos com sucesso!\n");

        System.out.println("=== Livros cadastrados ===");
        TypedQuery<Livro> queryLivros = em.createQuery("SELECT l FROM Livro l", Livro.class);
        List<Livro> livros = queryLivros.getResultList();

        for (Livro l : livros) {
            System.out.println("\nLivro: [" + l.getId() + "] " + l.getTitulo());
            System.out.println("  Ano: " + l.getAnoPublicacao());
            System.out.println("  ISBN: " + l.getIsbn());
            System.out.println("  Preço: R$ " + l.getPreco());
            System.out.println("  Tipo: " + l.getTipo());
            System.out.println("  Editora: " + l.getEditora().getNome() + " (" + l.getEditora().getCidade() + ")");
            System.out.println("  Autores:");
            for (Autor a : l.getAutores()) {
                System.out.println("    - " + a.getNome() + " [" + a.getNacionalidade() + "]");
            }
        }

        System.out.println("\n=== Autores cadastrados ===");
        TypedQuery<Autor> queryAutores = em.createQuery("SELECT a FROM Autor a", Autor.class);
        List<Autor> autores = queryAutores.getResultList();
        for (Autor a : autores) {
            System.out.println("\nAutor: [" + a.getId() + "] " + a.getNome() + " — " + a.getNacionalidade());
        }

        System.out.println("\n=== Editoras cadastradas ===");
        TypedQuery<Editora> queryEditoras = em.createQuery("SELECT e FROM Editora e", Editora.class);
        List<Editora> editoras = queryEditoras.getResultList();
        for (Editora e : editoras) {
            System.out.println("\nEditora: [" + e.getId() + "] " + e.getNome() + " — " + e.getCidade());
        }

        em.close();
        emf.close();

        System.out.println("\n*** Fim da execução ***");
    }
}
