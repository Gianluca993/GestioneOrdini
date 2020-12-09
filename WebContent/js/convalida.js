//carica la pagina solo quando è pronta
$(document).ready(function(){
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
                    notEmpty: {
                        message: 'Il nome non pu&ograve; essere vuoto!'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z][a-zA-Z ,.']{1,29}$/,
                        message: 'Da 2 a 30 caratteri. Solo lettere.'
                    }
                }
            },
            cognome: {
                container: '#infoCognome',
                validators: {
                    notEmpty: {
                        message: 'Il cognome non pu&ograve; essere vuoto!'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z][a-zA-Z ,.']{1,29}$/,
                        message: 'Da 2 a 30 caratteri. Solo lettere.'
                    }
                }
            },
            indirizzo : {
				container : '#infoIndirizzo',
				validators : {
					notEmpty : {
						message : 'Il campo indirizzo non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z ,.'-]+[0-9]{1,4}$/,
						message : 'Formato indirizzo (Via | Viale | Piazza nome n&deg; civico)'
					}
				}
			},	
            cap: {
                container: '#infoCap',
                validators: {
                    notEmpty: {
                        message: 'il CAP non pu&ograve; essere vuoto!'
                    },
                    regexp: {
                        regexp: /^[0-9]{5}$/,
                        message: 'Solo 5 caratteri. Solo Numeri.'
                    }
                }
            },
            nascita : {
				container : '#infoNascita',
				validators : {
					notEmpty : {
						message : 'Il campo nascita non pu&ograve; essere vuoto'
					},
					date : {
						format : 'DD/MM/YYYY',
						message : 'Data non valida. Inserirla nel formato DD-MM-YYYY'
                    }
                }
            },
            username: {
                container: '#infoUsername',
                validators: {
                    notEmpty: {
                        message: 'L\'username non pu&ograve; essere vuoto!'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]{1}[a-zA-z0-9-.]{1,9}$/,
                        message: 'Da 2 a 10 caratteri. Solo lettere, numeri o - _ .'
                    }
                }
            },
            password: {
                container: '#infoPassword',
                validators: {
                    notEmpty: {
                        message: 'La password non pu&ograve; essere vuota!'
                    },
                    regexp: {
                        regexp: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\d])[a-zA-Z0-9]{4,20}$/,
                        message: 'Minimo 4 caratteri, almeno una lettera minuscola, almeno una lettera maiuscola'
                    }
                }
            },
            email: {
                container: '#infoEmail',
                validators: {
                    notEmpty: {
                        message: 'L\'E-mail non pu&ograve; essere vuota!'
                    },
                    regexp: {
                        regexp: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/,
                        message: 'Inserisci una mail valida'
                    }
                }
            }
        }
    });
});