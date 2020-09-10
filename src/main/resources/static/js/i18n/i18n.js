import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)

export const i18n = new VueI18n({
	locale : 'fr',
	fallbackLocale : 'en',
	messages : {
		en : {		
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
			noprojects: 'Pour commencer votre premier projet de changement de poids, saisissez votre objectif de poids'
		},
		
		fr : {
			user:'Utilisateurs'
		}	
	}
})