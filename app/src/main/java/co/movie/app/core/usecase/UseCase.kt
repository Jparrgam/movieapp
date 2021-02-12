package co.movie.app.core.usecase

import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class UseCase<out ReturnType, in Params> where ReturnType : Any {

    abstract suspend fun run(params: Params): Flow<Either<Failure, ReturnType>>

    operator fun invoke(params: Params, onResult: (Flow<Either<Failure, ReturnType>>) -> Unit = {}) {
        val job = GlobalScope.async { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult.invoke(job.await()) }
    }

    class None
}