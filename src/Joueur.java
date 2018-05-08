import java.util.Set;

public class Joueur {
private Set listeUnite;

public Set getListeUnite() {
	return this.listeUnite;
}

public void addUnite(Unite u) {
	this.listeUnite.add(u);
}
}
