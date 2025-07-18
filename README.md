# Vote numérique des délégués - Cballot

## Descriptif du projet

Le vote des délégués pour la formation approche et cette année l'Afpa souhaite mettre en place le vote numérique.

L'objectif est de développer une application web comportant deux modules :

- une module application présentant une interface d'administration permettant à une personne habilitée de créer des sessions de formation, y associer des stagiaires et organiser une séance de vote ;
- un module permettant aux stagiaires d'une formation de choisir un binôme de délégué.e.s qui se présentent, ceci pour une séance de vote pré-organisée.

Le vote devra être anonyme (rien de l'identité des votants ne devra être stocké en BDD, il n'y aura que leurs choix).

Le nom du projet est libre, il vous est proposé "Cballot" pour "Class Ballot".

---

## Détails des fonctionnalités

Cette partie détaille :
1. Les rôles des utilisateurs 
2. **"Organisation de scrutin"** : module d'organisation du vote
3. **"Isoloir"** : module de vote pour les stagiaires 

### Rôles des utilisateurs

Dans le cadre du projet CBallot, deux rôles principaux sont définis pour les utilisateurs :
- Stagiaire votants
    - participants aux sessions de formation ;
    - accèdent à l'interface de vote via un code unique ;
    - ne peuvent voter qu'une seule fois.

- Organisateur de scrutin (formateur / responsable de formation / assistant de formation)
    - responsables de l'organisation et de la gestion des scrutins ;
    - créer des sessions de formation ;
    - ajouter et supprimer des stagiaires ;
    - planifier des séances de vote ;
    - gérer les binômes de candidats.

### Module "organisation de scrutin"

#### Création des sessions de formation

L'administrateur doit pouvoir créer une nouvelle session de formation en spécifiant les détails suivants :
- nom de la session
- date de début et de fin
- liste des stagiaires participants

> [!NOTE]
> Une session de formation est rattachée à une formation pré-existante.
>
> Par exemple, la formation CDA Brest se déroulant du 24/04/2026 au 13/02/2027 est une session de la formation **CDA**.

#### Organisation d'un scrutin

**Planification d'un scrutin**

L'administrateur doit pouvoir planifier une séance de vote pour une session de formation en spécifiant :
- date et heure du vote
- liste des binômes candidats

Un QR code devra être généré par l'application. Ce QR code permettra au votant d'arriver sur la page "isoloir" (le fonctionnement du vote sera détaillé dans la partie "Module isoloir").

Le QR code doit pouvoir être généré uniquement à l'heure du début du scrutin et ne plus être accessible une fois le scrutin terminé.

**Gestion des binômes candidats**
L'administrateur doit pouvoir ajouter, modifier et supprimer des binômes de candidats pour un scrutin.

Un binôme de candadidats est composé d'un **délégué principal** et d'un **suppléant**.

### Module "isoloir"

Cette partie détail le fonctionnement du module de vote dédié aux stagiaires.

**Interface de vote**
Les stagiaires doivent pouvoir accéder à une interface de vote où ils peuvent voir la liste des binômes candidats et voter pour un binôme.

Afin d'accéder à cette interface il leut faudra rentrer un code unique de vote.

**Code unique et anonymat du vote**
Le vote doit être anonyme, seul le choix du votant doit être stocké en base de données.

Afin de comptabiliser les votes un numéro unique (tel qu'un [UUID](https://www.postgresql.org/docs/current/datatype-uuid.html)) pourra être généré pour chaque stagiaire votant.

Ce code pourra être envoyé par email aux participant afin qu'il puisse débuter son vote.

> [!CAUTION]
> Pour des raisons d'anonymat le numéro unique des votants ne devra pas rester en base de données et devra être supprimé une fois le vote effectué. 
>
> Il est seulement utiliser pour permettre à un stagiaire de voter et assure qu'une personne ne **vote pas plusieurs fois**.

