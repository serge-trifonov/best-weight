import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)

export const i18n = new VueI18n({
	locale : 'fr',
	fallbackLocale : 'en',
	messages : {
		fr : {		
			users:'Users',
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
			noprojects: 'Pour commencer votre premier projet de changement de poids, saisissez votre objectif de poids',
			description: 'Après la saisie de l\'objectif de poids vous verrez la graphe de changement de poids attendu en prenant la vitesse moyenne pour le processus de changement (augmentation ou perte) saine. Par la suite vous pourrez saisir les resultats de vos mesures de poids et les comparer avec les mesures attendu. Cela vous permetra d\'avoir une idée sur les delais de votre projet de changement de poids et évitera d\'avoir les expectations démesurées.'
		},
		en : {
			user:'Utilisateurs'
		}	
	}
})