package esprit.tn.projetspring1.Service;

import esprit.tn.projetspring1.entity.Bloc;
import esprit.tn.projetspring1.entity.Chambre;
import esprit.tn.projetspring1.entity.typeC;

import java.util.List;

public interface IChambreservice {
    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);

     long nbChambreParTypeEtBloc(typeC typeChambre, Long idBloc );
}