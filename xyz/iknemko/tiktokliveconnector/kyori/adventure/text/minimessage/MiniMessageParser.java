/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.Examiner
 *  net.kyori.examination.string.MultiLineStringExaminer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ArgumentQueueImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ContextImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageParser$1
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser$TagProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.RootNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ValueNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Modifying
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.Examiner;
import net.kyori.examination.string.MultiLineStringExaminer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ArgumentQueueImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ContextImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.RootNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ValueNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Modifying;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node;

final class MiniMessageParser {
    final TagResolver tagResolver;

    @NotNull
    String stripTokens(@NotNull ContextImpl context) {
        StringBuilder sb = new StringBuilder(context.message().length());
        this.processTokens(sb, context, (token, builder) -> {});
        return sb.toString();
    }

    private Component handleModifying(Modifying modTransformation, Component current, int depth) {
        Component newComp = modTransformation.apply(current, depth);
        Iterator iterator = current.children().iterator();
        while (iterator.hasNext()) {
            Component child = (Component)iterator.next();
            newComp = newComp.append(this.handleModifying(modTransformation, child, depth + 1));
        }
        return newComp;
    }

    @NotNull
    Component parseFormat(@NotNull ContextImpl context) {
        RootNode root = this.parseToTree(context);
        return Objects.requireNonNull((Component)context.postProcessor().apply(this.treeToComponent((ElementNode)root, context)), "Post-processor must not return null");
    }

    MiniMessageParser(TagResolver tagResolver) {
        this.tagResolver = tagResolver;
    }

    private void visitModifying(Modifying modTransformation, ElementNode node, int depth) {
        modTransformation.visit((Node)node, depth);
        Iterator iterator = node.unsafeChildren().iterator();
        while (iterator.hasNext()) {
            ElementNode child = (ElementNode)iterator.next();
            this.visitModifying(modTransformation, child, depth + 1);
        }
    }

    private void processTokens(@NotNull StringBuilder sb, @NotNull ContextImpl context, BiConsumer<Token, StringBuilder> tagHandler) {
        this.processTokens(sb, context.message(), context, tagHandler);
    }

    MiniMessageParser() {
        this.tagResolver = TagResolver.standard();
    }

    private void escapeTokens(StringBuilder sb, String richMessage, ContextImpl context) {
        this.processTokens(sb, richMessage, context, (token, builder) -> {
            builder.append('\\').append('<');
            if (token.type() == TokenType.CLOSE_TAG) {
                builder.append('/');
            }
            List childTokens = token.childTokens();
            int i = 0;
            while (true) {
                if (i >= childTokens.size()) {
                    builder.append('>');
                    return;
                }
                if (i != 0) {
                    builder.append(':');
                }
                this.escapeTokens((StringBuilder)builder, ((Token)childTokens.get(i)).get((CharSequence)richMessage).toString(), context);
                ++i;
            }
        });
    }

    private void processTokens(@NotNull StringBuilder sb, @NotNull String richMessage, @NotNull ContextImpl context, BiConsumer<Token, StringBuilder> tagHandler) {
        Token token;
        TagResolver combinedResolver = TagResolver.resolver((TagResolver[])new TagResolver[]{this.tagResolver, context.extraTags()});
        List root = TokenParser.tokenize((String)richMessage, (boolean)true);
        Iterator iterator = root.iterator();
        block4: while (iterator.hasNext()) {
            token = (Token)iterator.next();
            switch (1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[token.type().ordinal()]) {
                case 1: {
                    sb.append(richMessage, token.startIndex(), token.endIndex());
                    continue block4;
                }
                case 2: 
                case 3: 
                case 4: {
                    if (token.childTokens().isEmpty()) {
                        sb.append(richMessage, token.startIndex(), token.endIndex());
                        continue block4;
                    }
                    String sanitized = TokenParser.TagProvider.sanitizePlaceholderName((String)((Token)token.childTokens().get(0)).get((CharSequence)richMessage).toString());
                    if (combinedResolver.has(sanitized)) {
                        tagHandler.accept(token, sb);
                        continue block4;
                    }
                    sb.append(richMessage, token.startIndex(), token.endIndex());
                    continue block4;
                }
            }
        }
        return;
        throw new IllegalArgumentException("Unsupported token type " + token.type());
    }

    @NotNull
    String escapeTokens(@NotNull ContextImpl context) {
        StringBuilder sb = new StringBuilder(context.message().length());
        this.escapeTokens(sb, context);
        return sb.toString();
    }

    void escapeTokens(StringBuilder sb, @NotNull ContextImpl context) {
        this.escapeTokens(sb, context.message(), context);
    }

    @NotNull
    RootNode parseToTree(@NotNull ContextImpl context) {
        TagResolver combinedResolver = TagResolver.resolver((TagResolver[])new TagResolver[]{this.tagResolver, context.extraTags()});
        String processedMessage = (String)context.preProcessor().apply(context.message());
        Consumer debug = context.debugOutput();
        if (debug != null) {
            debug.accept("Beginning parsing message ");
            debug.accept(processedMessage);
            debug.accept("\n");
        }
        TokenParser.TagProvider transformationFactory = debug != null ? (name, args, token) -> {
            try {
                debug.accept("Attempting to match node '");
                debug.accept(name);
                debug.accept("'");
                if (token != null) {
                    debug.accept(" at column ");
                    debug.accept(String.valueOf(token.startIndex()));
                }
                debug.accept("\n");
                @Nullable Tag transformation = combinedResolver.resolve(name, (ArgumentQueue)new ArgumentQueueImpl((Context)context, args), (Context)context);
                if (transformation == null) {
                    debug.accept("Could not match node '");
                    debug.accept(name);
                    debug.accept("'\n");
                } else {
                    debug.accept("Successfully matched node '");
                    debug.accept(name);
                    debug.accept("' to tag ");
                    debug.accept(transformation instanceof Examinable ? ((Examinable)transformation).examinableName() : transformation.getClass().getName());
                    debug.accept("\n");
                }
                return transformation;
            }
            catch (ParsingException e) {
                ParsingExceptionImpl impl;
                if (token != null && e instanceof ParsingExceptionImpl && (impl = (ParsingExceptionImpl)e).tokens().length == 0) {
                    impl.tokens(new Token[]{token});
                }
                debug.accept("Could not match node '");
                debug.accept(name);
                debug.accept("' - ");
                debug.accept(e.getMessage());
                debug.accept("\n");
                return null;
            }
        } : (name, args, token) -> {
            try {
                return combinedResolver.resolve(name, (ArgumentQueue)new ArgumentQueueImpl((Context)context, args), (Context)context);
            }
            catch (ParsingException ignored) {
                return null;
            }
        };
        Predicate<String> tagNameChecker = name -> {
            String sanitized = TokenParser.TagProvider.sanitizePlaceholderName((String)name);
            return combinedResolver.has(sanitized);
        };
        String preProcessed = TokenParser.resolvePreProcessTags((String)processedMessage, (TokenParser.TagProvider)transformationFactory);
        context.message(preProcessed);
        RootNode root = TokenParser.parse((TokenParser.TagProvider)transformationFactory, tagNameChecker, (String)preProcessed, (String)processedMessage, (boolean)context.strict());
        if (debug == null) return root;
        debug.accept("Text parsed into element tree:\n");
        debug.accept(root.toString());
        return root;
    }

    @NotNull
    Component treeToComponent(@NotNull ElementNode node, @NotNull ContextImpl context) {
        Consumer debug;
        TextComponent comp = Component.empty();
        Tag tag = null;
        if (node instanceof ValueNode) {
            comp = Component.text((String)((ValueNode)node).value());
        } else if (node instanceof TagNode) {
            TagNode tagNode = (TagNode)node;
            tag = tagNode.tag();
            if (tag instanceof Modifying) {
                Modifying modTransformation = (Modifying)tag;
                this.visitModifying(modTransformation, (ElementNode)tagNode, 0);
                modTransformation.postVisit();
            }
            if (tag instanceof Inserting) {
                comp = ((Inserting)tag).value();
            }
        }
        if (!node.unsafeChildren().isEmpty()) {
            ArrayList<Component> children = new ArrayList<Component>(comp.children().size() + node.children().size());
            children.addAll(comp.children());
            for (ElementNode child : node.unsafeChildren()) {
                children.add(this.treeToComponent(child, context));
            }
            comp = comp.children(children);
        }
        if (tag instanceof Modifying) {
            comp = this.handleModifying((Modifying)tag, (Component)comp, 0);
        }
        if ((debug = context.debugOutput()) == null) return comp;
        debug.accept("==========\ntreeToComponent \n");
        debug.accept(node.toString());
        debug.accept("\n");
        debug.accept(((Stream)comp.examine((Examiner)MultiLineStringExaminer.simpleEscaping())).collect(Collectors.joining("\n")));
        debug.accept("\n==========\n");
        return comp;
    }
}
