package br.com.eliascoelho911.notas.exception

import java.lang.RuntimeException

class MainViewModelNaoConfigurado : RuntimeException(
    "O View Model não foi configurado pela activity"
)