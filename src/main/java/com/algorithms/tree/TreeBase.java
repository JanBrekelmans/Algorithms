package com.algorithms.tree;

import com.algorithms.util.Pair;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import org.antlr.v4.runtime.tree.Tree;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class TreeBase<T, Node extends TreeBase<T, Node>.TreeNode> {
    protected final Comparator<T> comparator;
    protected Node root;

    public TreeBase(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public abstract void insert(T value);

    public abstract boolean remove(T value);

    public abstract int size();

    public void inorderWalk(Consumer<T> cons) {
        inorderWalk(node -> cons.accept(node.value), root);
    }

    protected void inorderWalk(Consumer<Node> cons, Node current) {
        if (current != null) {
            inorderWalk(cons, current.leftChild());
            cons.accept(current);
            inorderWalk(cons, current.rightChild());
        }
    }

    public void preorderWalk(Consumer<T> cons) {
        preorderWalk(node -> cons.accept((T) node.value), root);
    }

    protected void preorderWalk(Consumer<Node> cons, Node current) {
        if (current != null) {
            cons.accept(current);
            preorderWalk(cons, current.leftChild());
            preorderWalk(cons, current.rightChild());
        }
    }

    public boolean contains(T value) {
        return find(value) != null;
    }

    protected Node find(T value) {
        TreeNode current = root;

        while (current != null) {
            int cmp = comparator.compare(value, current.value);
            if (cmp < 0) {
                current = current.leftChild();
            } else if (cmp > 0) {
                current = current.rightChild();
            } else {
                return current.asNode();
            }
        }

        return null;
    }

    public T minimum() {
        TreeNode m = minimum(root);
        return m != null ? m.value : null;
    }

    protected Node minimum(TreeNode current) {
        while (current != null && current.leftChild() != null) {
            current = current.leftChild();
        }
        return current != null ? current.asNode() : null;
    }

    public T maximum() {
        TreeNode m = maximum(root);
        return m != null ? m.value : null;
    }

    protected TreeNode maximum(TreeNode current) {
        while (current != null && current.rightChild() != null) {
            current = current.rightChild();
        }
        return current;
    }

    protected boolean contains(T value, Node current) {
        if (current == null) {
            return false;
        } else return current.value.equals(value);
    }

    protected String getNodeColor(Node node) {
        return "black";
    }

    protected String getVertexColor(Node parentNode, Node childNode) {
        return "black";
    }

    protected class TreeNode {
        public T value;
        public TreeNode parent, leftChild, rightChild;

        public TreeNode(T value) {
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        public Node asNode() {
            return (Node) this;
        }

        @SuppressWarnings("unchecked")
        public Node parent() {
            return (Node) parent;
        }

        @SuppressWarnings("unchecked")
        public Node leftChild() {
            return (Node) leftChild;
        }

        @SuppressWarnings("unchecked")
        public Node rightChild() {
            return (Node) rightChild;
        }
    }


    public void createTreeImage(String filePath) throws IOException {
        File imgFile = new File(filePath);

        DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        Map<DefaultEdge, Pair<Node, Node>> parentChildToEdgeMap = new HashMap<>();

        // Fill the graph with the nodes and edges stored in this tree
        preorderWalk(node -> {
            g.addVertex(node.value.toString());
            if (node.parent() != null) {
                parentChildToEdgeMap.put(g.addEdge(node.parent().value.toString(), node.value.toString()), new Pair<>(node.parent(), node.asNode()));
            }
        }, root);

        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(g);
        mxCompactTreeLayout layout = new mxCompactTreeLayout(graphAdapter, false);
        layout.execute(graphAdapter.getDefaultParent());

        // Set the layout for each node, and set the value of each edge to "" (i.e. no text added to
        preorderWalk(node -> {
            mxICell cell = graphAdapter.getVertexToCellMap().get(node.value.toString());
            layout.getGraph().setCellStyles(mxConstants.STYLE_STROKECOLOR, getNodeColor(node.asNode()), new mxICell[]{cell});

        }, root);
        graphAdapter.getEdgeToCellMap().forEach((edge, cell) -> {
            Pair<Node, Node> nodePair = parentChildToEdgeMap.get(edge);
            cell.setValue("");
            layout.getGraph().setCellStyles(mxConstants.STYLE_STROKECOLOR, getVertexColor(nodePair.a, nodePair.b), new mxICell[]{cell});
        });


        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        ImageIO.write(image, "PNG", imgFile);
    }
}
