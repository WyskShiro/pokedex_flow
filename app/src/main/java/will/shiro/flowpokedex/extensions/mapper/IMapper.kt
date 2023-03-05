package will.shiro.flowpokedex.extensions.mapper

interface IMapper<I, O> {
    fun mapItem(input: I): O
    fun mapList(input: List<I>): List<O> {
        return input.mapNotNull {
            mapItem(input = it)
        }
    }
}