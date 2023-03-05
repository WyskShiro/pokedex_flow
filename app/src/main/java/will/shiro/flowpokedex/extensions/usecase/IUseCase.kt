package will.shiro.flowpokedex.extensions.usecase

interface IUseCase<I, O> {

    suspend operator fun invoke(input: I): O
}