/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.concurrent.FutureCallback
 *  org.apache.http.pool.AbstractConnPool
 */
package org.apache.http.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.pool.AbstractConnPool;

/*
 * Exception performing whole class analysis ignored.
 */
class AbstractConnPool.2
implements Future<E> {
    private final AtomicReference<E> entryRef;
    private final AtomicBoolean done;
    final /* synthetic */ Object val$route;
    final /* synthetic */ FutureCallback val$callback;
    final /* synthetic */ Object val$state;
    private final AtomicBoolean cancelled = new AtomicBoolean(false);

    @Override
    public E get() throws ExecutionException, InterruptedException {
        try {
            return this.get(0L, TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException ex) {
            throw new ExecutionException(ex);
        }
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled.get();
    }

    AbstractConnPool.2(FutureCallback futureCallback, Object object, Object object2) {
        this.val$callback = futureCallback;
        this.val$route = object;
        this.val$state = object2;
        this.done = new AtomicBoolean(false);
        this.entryRef = new AtomicReference<Object>(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (!this.done.compareAndSet(false, true)) return false;
        this.cancelled.set(true);
        AbstractConnPool.access$000((AbstractConnPool)AbstractConnPool.this).lock();
        try {
            AbstractConnPool.access$100((AbstractConnPool)AbstractConnPool.this).signalAll();
        }
        finally {
            AbstractConnPool.access$000((AbstractConnPool)AbstractConnPool.this).unlock();
        }
        if (this.val$callback == null) return true;
        this.val$callback.cancelled();
        return true;
    }

    /*
     * Exception decompiling
     */
    @Override
    public E get(long timeout, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[UNCONDITIONALDOLOOP]], but top level block is 1[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompile(CFRDecompiler.java:89)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompileToZip(CFRDecompiler.java:133)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.decompileSaveAll(ResourceDecompiling.java:261)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.lambda$decompileSaveAll$0(ResourceDecompiling.java:111)
         *     at java.base/java.lang.Thread.run(Thread.java:1575)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public boolean isDone() {
        return this.done.get();
    }
}
