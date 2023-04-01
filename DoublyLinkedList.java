public class DoublyLinkedList<T> {
    private DoublyLink<T> first; 
    private DoublyLink<T> last; 
    
    public DoublyLinkedList() {
        first = null; 
        last = null;
    }

                                /**
     * 
     * Metodo para verificar si la lista es vacia
     * @return devuelve True si la lista esta vacia, y devuelve False si la lista tiene al menos 1 elemento
     */
    public boolean isEmpty() { 
        return first==null; 
    }
    
         /**
     * 
     * Metodo para agregar un elemento de primero en la lista
     *@param dd valor que se desea agregar al nuevo nodo
     */
    public void insertFirst(T dd){
        DoublyLink<T> newLink = new DoublyLink<T>(dd); 
        if( isEmpty() ) 
            last = newLink; 
        else
            first.previous = newLink;
        newLink.next = first;
        first = newLink;
    }

                    /**
     * 
     * Metodo para agregar un elemento de ultimo en la lista
     * @param dd valor que se desea agregar al nuevo nodo
     */
    public void insertLast(T dd) {
        DoublyLink<T> newLink = new DoublyLink<T>(dd); 
        if( isEmpty() ) 
            first = newLink; 
        else {
            last.next = newLink; 
            newLink.previous = last; 
        }
        last = newLink; 
    }

                /**
     * 
     * Metodo para eliminar el primer elemento de la lista
     * @return devuelve el nodo eliminado
     */
    public DoublyLink<T> deleteFirst() { 
        DoublyLink<T> temp = first;
        if(first.next == null) 
            last = null;
        else
            first.next.previous = null;
        first = first.next; 
        return temp;
    }

                /**
     * 
     * Metodo para eliminar el ultimo elemento de la lista
     * @return devuelve el nodo eliminado
     */
    public DoublyLink<T> deleteLast(){
        DoublyLink<T> temp = last;
        if(first.next == null) 
            first = null; 
        else
            last.previous.next = null; 
        last = last.previous; 
        return temp;
    }
    
                /**
     * 
     * Metodo para insertar un nuevo elemento a la lista despues de un elemento ya dado en la lista
     * @param key es el valor del dato guardado del nodo que ya se encuentra en la lista
     * @param dd es el valor que el nuevo nodo almacenara
     * @return Verdadero si se logro agregar a la lista, Y Falso si no se encontro el dato dado en Key
     */
    public boolean insertAfter(T key, T dd) { 
        DoublyLink<T> current = first; 
        while(current.dData != key) {
            current = current.next; 
            if(current == null)
                return false; 
        }
        DoublyLink<T> newLink = new DoublyLink<T>(dd);
        if(current==last) {
            newLink.next = null; 
            last = newLink;
        }
        else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        newLink.previous = current; 
        current.next = newLink; 
        return true; 
    }
    
                    /**
     * 
     * Metodo para eliminar un nodo ya guardado en la lista mediante su dato
     * @param key es el valor del dato guardado del nodo que ya se encuentra en la lista
     * @return devuelve el nodo eliminado
     */
    public DoublyLink<T> deleteKey(T key) { 
        DoublyLink<T> current = first; 
        while(current.dData != key) {
            current = current.next; 
            if(current == null)
            return null;
        }
        if(current==first)
            first = current.next;
        else
            current.previous.next = current.next;
        if(current==last) 
            last = current.previous;
        else
            current.next.previous = current.previous;
        return current;
    }
                        /**
     * 
     * Metodo para imprimir la lista desde el primer elemento hasta el ultimo elemento
     */
    public void displayForward() {
        System.out.print("List (first-->last): ");
        DoublyLink<T> current = first;
        while(current != null) {
            current.displayLink(); 
            current = current.next; 
        }
        System.out.println("");
    }

    

                            /**
     * 
     * Metodo para imprimir la lista desde el ultimo elemento hasta el primer elemento
     */
    public void displayBackward() {
        System.out.print("List (last-->first): ");
        DoublyLink<T> current = last;
        while(current != null) {
            current.displayLink(); 
            current = current.previous; 
        }
        System.out.println("");
    }

        /**
     * 
     * Metodo que devuelve el Primer Valor de la lista
     */
    public DoublyLink<T> GetFirst()
    {
        //System.out.print("First--> ");
        //first.displayLink();
        return(first);
    }

            /**
         * Metodo que devuelve el Ultimo Valor de la lista
         */
        public void GetLast() {
            last.displayLink();
            System.out.println("<--last");
        }

                /**
        * Metodo que devuelve el Tamano de la lista
        * @return Tamano actual de la lista.
        */
        public int GetSize() {
            int i = 0;
            DoublyLink<T> current = first;
            while(current != null) {
                current = current.next; 
                i++;
            }
            return i;
        }
  
            /**
     * 
     * Metodo para Actualizar un Nodo Dado un Valor a buscar:
     * CambiarPorValor(ValorABuscar, ValorACambiar)
     * @param ValorABuscar(Generico): Es el Dato Generico el Cual se desea Buscar
     * @param ValorACambiar(Generico): Es el Dato por el cual se va a reemplazar el dato a buscar
    */
    public void CambiarPorValor(T ValorABuscar, T ValorACambiar) {
        DoublyLink<T> current = first;
        boolean encontrado = false;
        while(current != null) {
            if(current.dData.equals(ValorABuscar))
            {
                encontrado = true;
                current.dData = ValorACambiar;
                break;
            }
            current = current.next; 
        }
        if(encontrado)
        {
            System.out.println("Valor Cambiado");
        }
        else
        {
            System.out.println("Valor no Encontrado");
        }
    }
    
        /**
    *Metodo Para eliminar un nodo en base a un Valor a buscar
    @param ValorABuscar(Generico) Es el valor que se desea eliminar de la lista*/
    public void EliminarPorValor(T ValorABuscar) {
        DoublyLink<T> current = first;
        boolean encontrado = false;
        if(first.dData.equals(ValorABuscar)&&!encontrado)
        {
            deleteFirst();
            encontrado = true;
        }
        if(last.dData.equals(ValorABuscar)&&!encontrado)
        {
            deleteLast();
            encontrado = true;
        }

        while(current.next != null&&!encontrado) {
            if(current.next.dData.equals(ValorABuscar))
            {
                encontrado = true;
                current.next.next.previous = current;
                current.next = current.next.next;
                break;
            }
            current = current.next; 
        }
        if(encontrado)
        {
            System.out.println("Valor Eliminado");
        }
        else
        {
            System.out.println("Valor no Encontrado");
        }
    }

        /**
    *Metodo para Buscar un Nodo en base a un valor dado
    @param ValorABuscar(Generico) Es el valor que se desea buscar en la lista
    */
    public void BuscarValor(T ValorABuscar) {
        DoublyLink<T> current = first;
        boolean encontrado = false;
        int i = 1;
        while(current != null) {
            if(current.dData.equals(ValorABuscar))
            {
                encontrado = true;
                break;
            }
            current = current.next;
            i++; 
        }
        if(encontrado)
        {
            System.out.println("Valor Encontrado en posicion: " + i );
        }
        else
        {
            System.out.println("Valor no Encontrado");
        }
    }

    /**
     * Metodo para Actualizar un Nodo Dado una posicion en la lista:
    @param posicion(Int): Es la posicion del nodo a Actualizar. comenzando desde 0 hasta el tamaño de la lista
    @param ValorACambiar(Generico): Es el Dato por el cual se va a reemplazar el dato a buscar
    */
    public void CambiarPorPosicion(int posicion, T ValorACambiar) {
        DoublyLink<T> current = first;
        int i = 0;
        while(current != null) {
            if(i == posicion)
            {
                current.dData = ValorACambiar;
                break;
            }
            i++;
            current = current.next; 
        }
    }

        /**
     * Metodo para Buscar un Nodo Dado una posicion en la lista:
    @param posicion(Int): Es la posicion del nodo a Actualizar. comenzando desde 0 hasta el tamaño de la lista menos 1
    */
    public T BuscarPorPosicion(int posicion) {
        DoublyLink<T> current = first;
        T valor = null;
        int i = 0;
        while(current != null) {
            if(i == posicion)
            {
                valor = current.dData;
                break;
            }
            i++;
            current = current.next;
        }
        return valor;
    }
        /**
    *Metodo para eliminar todos los datos de la lista
    */
    public void limpiarlista()
    {
        first = null;
        last = null;
    }

        /**
    *Metodo Para eliminar un nodo en base a una posicion en la lista
    @param posicion 
    *(int):Es la posicion del valor que se desea eliminar de la lista,  Comenzando desde 1 hasta el tamaño de la lista
    */
    public void EliminarPorPosicion(int posicion) {
        DoublyLink<T> current = first;
        int i = 1;
        boolean encontrado = false;
        if(posicion == 1)
        {
            deleteFirst();
            encontrado = true;
        }

        if(!encontrado && posicion == GetSize())
        {
            deleteLast();
            encontrado = true;
        }

        while(current.next != null&&!encontrado) {
            if(i + 1 == posicion)
            {
                encontrado = true;
                current.next = current.next.next;
                break;
            }
            i++;
            current = current.next; 
        }
        if(encontrado)
        {
            System.out.println("Valor Eliminado");
        }
        else
        {
            System.out.println("Posicion no Valida");
        }
    }



    
} 