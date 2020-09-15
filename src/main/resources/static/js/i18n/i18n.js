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
			messagedangersamedirection: 'Attention! Le changement de poids aussi rapide peut est dangereau pour votre santé'
		},
		en : {
			user:'Utilisateurs'
		}	
	}
})