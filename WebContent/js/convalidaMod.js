//carica la pagina solo quando è pronta
$(document).ready(function() {
	$('#userForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			nome: {
				container: '#infoNome',
				validators: {
					regexp: {
						regexp: /^[a-zA-Z][a-zA-Z ,.']{1,29}$/,
						message: 'Da 2 a 30 caratteri. Solo lettere.'
					}
				}
			},
			cognome: {
				container: '#infoCognome',
				validators: {
					regexp: {
						regexp: /^[a-zA-Z][a-zA-Z ,.']{1,29}$/,
						message: 'Da 2 a 30 caratteri. Solo lettere.'
					}
				}
			},
			indirizzo: {
				container: '#infoIndirizzo',
				validators: {
					regexp: {
						regexp: /^[a-zA-Z ,.'-]+[0-9]{1,4}$/,
						message: 'Formato indirizzo (Via | Viale | Piazza nome n&deg; civico)'
					}
				}
			},
			cap: {
				container: '#infoCap',
				validators: {
					regexp: {
						regexp: /^[0-9]{5}$/,
						message: 'Solo 5 caratteri. Solo Numeri.'
					}
				}
			},
			nascita: {
				container: '#infoNascita',
				validators: {
					date: {
						format: 'DD/MM/YYYY',
						message: 'Data non valida. Inserirla nel formato DD-MM-YYYY'
					}
				}
			},
			password: {
				container: '#infoPassword',
				validators: {
					regexp: {
						regexp: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\d])[a-zA-Z0-9]{4,20}$/,
						message: 'Minimo 4 caratteri, almeno una lettera minuscola, almeno una lettera maiuscola'
					}
				}
			},
			oldpassword: {
				container: '#infoOldPassword',
				validators: {
					regexp: {
						regexp: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\d])[a-zA-Z0-9]{4,20}$/,
						message: 'Minimo 4 caratteri, almeno una lettera minuscola, almeno una lettera maiuscola'
					}
				}
			},
			email: {
				container: '#infoEmail',
				validators: {
					regexp: {
						regexp: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/,
						message: 'Inserisci una mail valida'
					}
				}
			}
		}
	});
});