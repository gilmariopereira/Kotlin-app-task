package dev.gilmario.task.model

class PriorityCacheConstants private constructor() {

    companion object {

        private val mPriorityCache = hashMapOf<Int, String>()

        fun setCache(list: List<Priority>) {
            for(item in list) {
                mPriorityCache.put(item.id, item.descricao.toString())
            }
        }

        fun getPriorityDescription(id :Int?) :String {
            if(mPriorityCache[id] == null) {
                return ""
            }
            return mPriorityCache[id].toString()
        }

    }

}