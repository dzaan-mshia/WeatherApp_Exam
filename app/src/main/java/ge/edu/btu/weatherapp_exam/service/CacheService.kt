import java.util.concurrent.TimeUnit

class CacheService<K, T>(lifeTime: Long) {

    private var lifeTime = lifeTime

    private var map: HashMap<K, T> = HashMap()

    private var lastTimeCleared = System.nanoTime()

    fun put(key: K, element: T){
        map[key] = element
    }

    fun get(key: K): T? {
        recycle()
        return map[key]
    }

    fun contains(key: K): Boolean{
        recycle()
        return map.contains(key)
    }

    private fun recycle(){
        if (System.nanoTime() - lastTimeCleared >= TimeUnit.MINUTES.toNanos(lifeTime)){
            lastTimeCleared = System.nanoTime()
            map.clear()
        }
    }
}