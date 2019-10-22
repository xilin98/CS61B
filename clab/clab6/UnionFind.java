public class UnionFind {
    int[] lst;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        lst =new int[n];
        for (int i = 0; i < lst.length; i++) {
            lst[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= lst.length || vertex < 0) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int size = -lst[find(v1)];
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        int parent;
        if (lst[v1] < 0) {
            parent = v1;
        } else {
            parent = lst[v1];
        }
        return parent;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int r1 = find(v1);
        int r2 = find(v2);
        if(r1 != r2) {
            if (sizeOf(r1) > sizeOf(r2)) {
                lst[r1] -= sizeOf(r2);
                lst[r2] = r1;
            } else {
                lst[r2] -= sizeOf(r1);
                lst[r1] = r2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while(parent(vertex) != vertex) {
            vertex = parent(vertex);
        }
        while (parent(root) != vertex) {
            root = parent(root);
            lst[root] = vertex;
        }
        return vertex;
    }

}
