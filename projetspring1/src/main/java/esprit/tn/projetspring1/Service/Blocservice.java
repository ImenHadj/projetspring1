package esprit.tn.projetspring1.Service;

import esprit.tn.projetspring1.entity.Bloc;
import esprit.tn.projetspring1.entity.Chambre;
import esprit.tn.projetspring1.repository.Blocrepository;
import esprit.tn.projetspring1.repository.Chambrerepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j

@Service
@AllArgsConstructor
public class Blocservice implements IBlocservice {
    Blocrepository blocrepository;
    Chambrerepository chambrerepository;
    @Override
    public List<Bloc> retrieveAllBlocs() {
        return (List<Bloc>) blocrepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return (Bloc) blocrepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocrepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocrepository.findById(idBloc).get();
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocrepository.deleteById(idBloc);
    }
    @Override
    public Bloc affecterChambreABloc(List<Long> numeroChambre, String nomBloc) {
        Bloc bloc = blocrepository.findBynomBloc(nomBloc);
        for (Long numero : numeroChambre) {
            Chambre chambre = chambrerepository.findByNumeroChambre(numero);
            chambre.setBloc(bloc);
            chambrerepository.save(chambre);
        }

        return bloc;
    }

    @Override
    public Bloc desaffecterChambreDeBloc(List<Long> numeroChambre) {
        for (Long numero : numeroChambre) {
            Chambre chambre = chambrerepository.findByNumeroChambre(numero);
            if (chambre != null) {
                chambre.setBloc(null);
                chambrerepository.save(chambre);
            }
        }
        return null;
    }
    @Scheduled(fixedRate = 60000)
    void listeChambresParBloc() {
        List<Bloc> blocs = (List<Bloc>) blocrepository.findAll();
        blocs.forEach(ch -> {
            log.info("Bloc : " + ch.getNomBloc() + "ayant une capacité de : " + ch.getCapaciteBloc());
            log.info("Liste des chambres du bloc " + ch.getNomBloc());
            Set<Chambre> chambre = ch.getChambres();
            chambre.forEach(cha -> {
                log.info("chambre numéro " + cha.getNumeroChambre() + "de typr" + cha.getTypeChambre());
            });

        });
    }}

