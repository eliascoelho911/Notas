package br.com.eliascoelho911.notas.exception

import java.lang.RuntimeException

class LayoutInflaterNaoDefinido : RuntimeException(
    "O Layout Inflater precisa ser definido pela Activity"
)
