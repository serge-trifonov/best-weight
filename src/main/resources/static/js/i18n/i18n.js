import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)

export const i18n = new VueI18n({
	locale : 'fr',
	fallbackLocale : 'en',
	messages : {
		fr : {		
			users:'Utilisateurs',
			yourimc: 'Votre IMC',
			comments0: 'Saisissez votre poids et taille pour connaitre votre IMC',
			comments1: 'Dénutrition',
			comments2: 'Maigreur',
			comments3: 'Corpulence normale',
			comments4: 'Surpoids',
			comments5: 'Obésité modérée',
			comments6: 'Obésité sévère',
			comments7: 'Obésité morbide ou massive',
			bestweightinterval: 'Votre poids idéale se situe entre {min} et {max} kilos',
			noprojects: 'Pour commencer votre premier projet de changement de poids, saisissez votre poids actuel et votre objectif de poids',
			description: 'Après la saisie de l\'objectif de poids vous verrez la graphe de changement de poids attendu en prenant la vitesse moyenne pour le processus de changement (augmentation ou perte) saine. Par la suite vous pourrez saisir les resultats de vos mesures de poids et les comparer avec les mesures attendu. Cela vous permetra d\'avoir une idée sur les delais de votre projet de changement de poids et évitera d\'avoir les expectations démesurées.',
			fullproject: 'L\'aperçu global de votre projet',
			lastmonthproject: 'Vos resultats de dernières semaines',
			yourproject: 'Votre projet de changement de poids. Vous pouvez atteindre votre poids souhaité vers le {date}',
			weightkg: 'Poids en kg',
			expectedDatas: 'Les mesures attendus',
			realDatas: 'Vos mesures',
			changeproject: 'Vous pouvez commencer le nouveau projet de changement de poids qui remplacera le courant',
			reload: 'Mettre à jour',
			newproject: 'Commenser le nouveau projet',
			submit: 'Valider',
			weightgoal: 'Objectif de poids',
			noweightgoal: 'Vous n\'avez pas de projets de changement de poids en cours',
			noweight: 'Le poids actuel est obligatoire',
			messagesuccessoppositedirection: 'Votre poids est assez proche du poids attendu pour ce jour-ci.',
			messagesuccesssamedirection: 'Vous suivez bien le plan. Felicitations',
			messagewarningoppositedirection: 'Vous vous eloignez un peu trop de votre projet. Faites attention !',
			messagewarningsamedirection: 'Il ne faut pas trop précipiter les choses. Le changement de poids trop rapide peut être nocive pour votre santé',
			messagedangeroppositedirection: 'Malheureusement vous êtes beaucoup trop éloigné du plan. On vous conseille de commencer le nouveau projet de changement de poids.',
			messagedangersamedirection: 'Attention! Le changement de poids aussi rapide peut est dangereau pour votre santé',
			currentweight: 'Votre poids actuel',
			startweight: 'Le poids de depart',
			aboutprogram: 'Description',
			enter: 'Espace personnel',
			login: 'Connexion',
			registration: 'Registration',
			username: 'Nom d\'utilisateur',
			password: 'Mot de passe',
			email: 'Email',
			height: 'Taille en cm',
			notidentified: 'Les données saisies n\'ont pas permi de vous authentifier',
			nopassword: 'Veillez saisir un mot de passe de 3 caractères minimum',
			nousername: 'Le nom d\'utilisateur n\'est pas valide',
			noemail: 'Email n\'est pas correcte',
			noheight: 'La taille est obligatoire',
			itsademoversion: 'Ceci est une version demo de l\'application',
			miniversion: 'Cette version mini permet de decouvrir les fonctionnalités du projet sans inscription. Vous pouvez tout simplement saisir votre taille, votre poids actuel, le poids que vous aimeriez avoir et l\'application va vous donner votre imc et la graphe de changement de poids pour atteindre le poids que vous désirez. Cela vous permettra de voir vers quelle date vous  pourrez avoir le poids que vous desirez. L\'application dessine les graphes en prenant la vitesse recommendé du changement du poids , donc environs +-0.75 % de votre poids par semaine (quelle que soit votre envie, de perdre du poids ou de gagner en masse musculaire).',
			fullaccess: 'Si vous choisissez de vous inscrire (espace personnel) vous pourrez ajouter votre poids actuel au fur et à mesure d\'avancement de votre projet de perte de poids et comparez les resultats de vos mesures avec les mesures attendues, comme il est montré sur le dessin ci-dessous',
			logout: "se déconnecter"
		},
		en : {
			user:'Users'
		}	
	}
})