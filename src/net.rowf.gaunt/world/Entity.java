package jgaunt.world;


import java.util.*;

/**
 *
 * @author woeltjen
 */
public final class Entity implements Cloneable {
    private LinkedList<Component> components = new LinkedList<Component>();

    public Entity() {}

    public Entity(List<Component> components) {
        this.components.addAll(components);
    }

    public <C extends Component> Collection<C> first(Class<C> cClass) {
        for (Component c : components) if (cClass.isAssignableFrom(c.getClass())) return Collections.singleton((C) c);
        return Collections.emptyList();
    }

    public <C extends Component> Collection<C> get(Class<C> bClass) {
        Collection<C> subset = new ArrayList<C>();
        for (Component c : components) if (bClass.isAssignableFrom(c.getClass())) subset.add((C)c);
        return subset;
    }

    public void add (Component c) {
        components.add(c);
    }

    public void remove(Component c) {
        components.remove(c);
    }

    public <C extends Component> void removeAll(Class<C> cClass) {
        components.removeAll(get(cClass));
    }

    public <C extends Component> void set (Class<C> cClass, C c) {
        removeAll(cClass);
        add(c);
    }

    @Override
    public Entity clone() {
        return new Entity(components);
    }
    
}
