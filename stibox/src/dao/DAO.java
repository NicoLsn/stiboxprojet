package dao;

import java.util.List;

public interface DAO<T> {

public abstract List<T> afficherListe();
public abstract void ajouter(T objet);
public abstract void modifier(T objet);
public abstract void supprimer(T objet);
public abstract T getById(T objet);

}
