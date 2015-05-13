package wangheng.nclaptop;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * http://www.mitbbs.com/article_t/JobHunting/32829537.html
 * 
 * 给了一个interface Node 可以getParent()
然后input是collection of nodes
写个function，check这些nodes是不是一个tree
 */
public class IsATree {
    public boolean isATree(Set<Node> nodeSet) {
        Set<Node> visited = new HashSet<Node>();
        Node root = null;

        Iterator<Node> ite = nodeSet.iterator();
        while (ite.hasNext()) {
            Node node = ite.next();
            boolean isVisited = false;

            Set<Node> currPath = new HashSet<Node>();
            // also need to check if parent is in the given node set
            while (node.getParent() != null && nodeSet.contains(node.getParent())) {
                // Need to check if there is a cycle
                if (currPath.contains(node))
                    return false;

                if (visited.contains(node)) {
                    isVisited = true;
                    break;
                }

                visited.add(node);
                currPath.add(node);
                node = node.getParent();
            }

            if (isVisited)
                continue;

            if (root == null) {
                root = node;
                visited.add(node);
            } else {
                if (root != node)
                    return false;
            }

        }

        return true;
    }
}

interface Node {
    Node getParent();
}
